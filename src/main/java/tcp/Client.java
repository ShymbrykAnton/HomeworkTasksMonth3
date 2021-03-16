package tcp;

import java.io.*;
import java.net.Socket;


public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        try {
            clientSocket = new Socket("localhost", 4004);
            reader = new BufferedReader(new InputStreamReader(System.in));
            ServerConnection serverConnection =
                    new ServerConnection(clientSocket);

            out = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println("Say something");
            new Thread(serverConnection).start();

            while (true) {
                String msg = reader.readLine();
                if (msg.equalsIgnoreCase("exit")) {
                    break;
                }
                out.write(msg + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }
}

