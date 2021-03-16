package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerReal extends Thread {
    private final Socket socket;
    public ServerSocket server;
    public PrintWriter out;
    public BufferedReader in;
    private final ArrayList<ServerReal> users;

    public ServerReal(Socket clientSocket, ArrayList<ServerReal> users) throws IOException {
        this.socket = clientSocket;
        this.users = users;
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {

        try {

            while (true) {
                String msg = in.readLine();
                System.out.println(msg);
//                out.print("You write: " + msg + "\n");
                outToUsers(msg);
                out.flush();
                if (msg.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private  void outToUsers(String input) {
        for (ServerReal serverReal : users) {
            serverReal.out.println(input);
        }
    }


}
