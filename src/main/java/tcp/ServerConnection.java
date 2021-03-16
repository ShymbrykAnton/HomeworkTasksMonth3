package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private Socket server;
    public PrintWriter out;
    public BufferedReader in;

    public ServerConnection(Socket s) throws IOException {
        server = s;
        in = new BufferedReader(
                new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream());

    }

    @Override
    public void run() {

            String answer;
            try {
                while (true) {
                    answer = in.readLine();
                    if (answer == null) {
                        break;
                    }
                    System.out.println("From someone: " + answer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}