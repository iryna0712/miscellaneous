import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class InterruptibleSocketFrame extends JFrame {

    private JTextArea messages;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;

    private TestServer server;
    private Thread connectionThread;


    public InterruptibleSocketFrame() {
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        final int TEXT_ROWS = 20;
        final int TEXT_COLUMNS = 60;
        messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");
        cancelButton = new JButton("Blocking");

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);
        northPanel.add(cancelButton);

        cancelButton.setEnabled(true);
        cancelButton.addActionListener(event -> {
            if (connectionThread != null) {
                connectionThread.interrupt();
                cancelButton.setEnabled(false);
            }
        });

        interruptibleButton.addActionListener(event -> {

            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);

            connectionThread = new Thread(() -> {
                try {
                    connectInteruptibly();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            connectionThread.start();

        });

        blockingButton.addActionListener(event -> {

            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);

            connectionThread = new Thread(() -> {
                try {
                    connectBlocking();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            connectionThread.start();

        });

        //start server
        server = new TestServer();
        new Thread(server).start();
    }

    public void connectInteruptibly() throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189));

        try (Scanner in = new Scanner(channel, "UTF-8")) {
            while (!Thread.currentThread().isInterrupted()) {
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally {
            EventQueue.invokeLater(() -> {
                messages.append("Channel closed\n");

                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    public void connectBlocking() throws IOException {
        Socket socket = new Socket("localhost", 8189);

        try (Scanner in = new Scanner(socket.getInputStream(), "UTF-8")) {
            while (!Thread.currentThread().isInterrupted()) {
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }
    }


}
