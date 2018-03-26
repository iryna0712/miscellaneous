import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer implements Runnable {

    public TestServer() {
    }

    @Override
    public void run() {

        try {
            ServerSocket socket = new ServerSocket(8189);

            while (true) {
                Socket s = socket.accept();
                new Thread(new TestServerHandler(s)).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
