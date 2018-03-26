import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TestServerHandler implements Runnable {

    private Socket socket;

    public TestServerHandler(Socket s) {
        socket = s;
    }

    @Override
    public void run() {

        int counter = 0;
        try {
            try {
                OutputStream out = socket.getOutputStream();
                PrintWriter print = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));

                for (int i = 0; i < 100; i++) {
                    if (counter < 10) print.println(counter);
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
