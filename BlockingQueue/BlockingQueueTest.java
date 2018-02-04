import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.File;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.System;
import java.lang.InterruptedException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class BlockingQueueTest {

	private static final boolean USE_DEFAULT_VALUES = true;

	private static final String OUTPUT_FILENAME = BlockingQueueTest.class.getSimpleName() + "Result.txt";
	private static final String ENCODING = "UTF-8";

	private static final String DIRECTORY = "\\benchmark_texts";
	private static final String KEYWORD = "человек";
	private static String directory = USE_DEFAULT_VALUES ? DIRECTORY : "";
	private static String keyword = USE_DEFAULT_VALUES ? KEYWORD : "";

	private static final int QUEUE_LENGTH = 5;
	private static final int SEARCH_THREAD_NUMBER = 100;
	private static BlockingQueue<File> fileQueue = new LinkedBlockingQueue<>(QUEUE_LENGTH);
	//artificial file, signaling end of file
	private static final File END_OF_QUEUE_FILE = new File("");

	private static final boolean STANDARD = true;
	private static final boolean CUSTOM = false;
	private static final PrintStream stdOut = System.out;

	private static volatile boolean done;
	private static long startTime;


	public static void main(String[] args) {

		if (!USE_DEFAULT_VALUES) {
			promptUserForInput();
		}

		changeOutputSource(CUSTOM);

		//////////////////////// I. AtomicInteger benchmark ///////////////////////////////
		startTimer();
		Counters.AtomicIntegerCounter atomicCounter = new Counters.AtomicIntegerCounter();

		startFileEnumerationThread(directory);
		startSearchThroughFiles(fileQueue, atomicCounter);

 		System.out.println("\nNumber of keywords in text : " + atomicCounter.get());

 		changeOutputSource(STANDARD);
 		endTimerAndPrintExecutionTime("using AtomicInteger");

 		//////////////////////// II. LongAdder benchmark ///////////////////////////////
 		startTimer();
		Counters.LongAdderCounter longAdderCounter = new Counters.LongAdderCounter();

		startFileEnumerationThread(directory);
		startSearchThroughFiles(fileQueue, longAdderCounter);

 		System.out.println("\nNumber of keywords in text : " + longAdderCounter.get());

 		changeOutputSource(STANDARD);
 		endTimerAndPrintExecutionTime("using LongAdder");
	}

	private static void promptUserForInput() {
		try (Scanner in = new Scanner(System.in, "UTF-8")) {
			boolean dirEntered = false;
			boolean keywordEntered = false;

			while (!dirEntered || !keywordEntered) {
				try {
					if (!dirEntered) {
						System.out.println("Enter directory: ");
						directory = in.nextLine();
						if (!directory.isEmpty()) {
							dirEntered = true;
						}
					}

					if (!keywordEntered) {
						System.out.println("Enter keyword: ");
						keyword = in.nextLine();
						if (!keyword.isEmpty()) {
							keywordEntered = true;
						}
					}
				} catch (NoSuchElementException e) {
					System.out.println("No input was found. Try again.");
				}
			}
		}
	}

	private static void changeOutputSource(boolean isStandard) {
		try {
			PrintStream stream = isStandard ? stdOut :
				new PrintStream(new FileOutputStream(OUTPUT_FILENAME), true, ENCODING);
			System.setOut(stream);
		} catch (UnsupportedEncodingException e) {

		} catch (FileNotFoundException e) {

		}
	}

	private static void startTimer() {
		startTime = System.currentTimeMillis();
	}

	private static void	endTimerAndPrintExecutionTime(String testName) {
		long endTime = System.currentTimeMillis();
 		long executionTime = (endTime - startTime);
 		long milliseconds = executionTime % 1000;
		long second = (executionTime / 1000) % 60;
		long minute = (executionTime / (1000 * 60)) % 60;
		long hour = (executionTime / (1000 * 60 * 60)) % 24;

		String executionTimeString = String.format("%02d:%02d:%02d:%03d", hour, minute, second, milliseconds);
		System.out.println("Execution time " + testName + " : " + executionTimeString);
	}

	private static void readAndRememberFiles(File file) throws InterruptedException {
		if (file.isDirectory()) {
			File[] fileList = file.listFiles();

			for (File singleFileFromList : fileList) {
				changeOutputSource(CUSTOM);
				System.out.println("File read: " + singleFileFromList);
				readAndRememberFiles(singleFileFromList);
			}
		} else {
			fileQueue.put(file);
			changeOutputSource(CUSTOM);
			System.out.println("File put: " + file);
		}
	}

	private static void startFileEnumerationThread(String dir) {
		final File dirFile = new File(dir);
		Runnable fileReader = () -> {
			try {
				readAndRememberFiles(dirFile);
				fileQueue.put(END_OF_QUEUE_FILE);
			} catch (InterruptedException e) {
				changeOutputSource(STANDARD);
				System.out.println("Reading was interrupted");
			}
		};
		new Thread(fileReader).start();
	}

	private static void startSearchThroughFiles(BlockingQueue<File> queue, Counters.Counter counter) {
		for (int i = 1; i <= SEARCH_THREAD_NUMBER; i++) {

			Runnable searcher = () -> {

				while (!done) {
					try {
						File file = fileQueue.take();
						changeOutputSource(STANDARD);
						System.out.println(file);
						if (file == END_OF_QUEUE_FILE) {
							done = true;
							changeOutputSource(STANDARD);
							System.out.println("done");
						} else {
							search(file, KEYWORD, counter);
						}
					} catch (InterruptedException e) {
						//TODO: handle
					}
				}
			};

			Thread searchThread = new Thread(searcher);
			searchThread.start();
			//wait for thread to complete execution before outputting the final value

			//TODO: why is Interrupted exception possible??
			try {
				searchThread.join();
			} catch (InterruptedException e) {
				//TODO:log
			}
		}
	}

	private static void search(final File file, final String keyword, Counters.Counter counter) {
		try {
			try (Scanner in = new Scanner(file)) {
				while (in.hasNext()) {
					String wordToCheck = in.next();

					if (wordToCheck.contains(keyword)) {
						counter.increment();
						changeOutputSource(STANDARD);
						System.out.println("Keyword found -> (" + wordToCheck + ")");
					}
				}
			}
		} catch (FileNotFoundException e) {
			changeOutputSource(STANDARD);
			System.out.println("File not found : " + file);
		}
	}
}