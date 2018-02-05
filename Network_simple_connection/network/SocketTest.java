package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {

    private static final int MILIS_TIMEOUT = 20000;
    private static final String HOST = "time-a.nist.gov";
    private static final int PORT = 13;

    public static void main(String[] args) throws IOException {

        try (   Socket socket = new Socket(HOST, PORT);
                Scanner in = new Scanner(socket.getInputStream(), "UTF-8")) {

            while(in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }

        } catch (IOException e) {
           throw e;
        }

        Socket timeoutedSocket = new Socket(HOST, PORT);
        timeoutedSocket.setSoTimeout(MILIS_TIMEOUT);

        try {
            InputStream inputStream = timeoutedSocket.getInputStream();
            //some actions
        } catch (InterruptedIOException e) {
            System.out.println("Timeout reached its end");
        }

        Socket socket2 = new Socket();
        socket2.connect(new InetSocketAddress(HOST, PORT));

        InetAddress inetAddress = InetAddress.getByName("google.com");
        System.out.println(inetAddress.getHostAddress());
        byte[] address = inetAddress.getAddress();

        ////////// important!! /////////////////////////
        for (byte add : address) {
            System.out.print((add  & 0xff) + ".");
        }
        System.out.println();

        InetAddress inetAddress2 = InetAddress.getLocalHost();
        System.out.println(inetAddress2);
    }

}
