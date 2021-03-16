package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static Socket clientSocket;
    private static ServerSocket server;
    private static ArrayList<ServerReal> list = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        server = new ServerSocket(4004);

        while (true) {
            System.out.println("Server: wait");
            clientSocket = server.accept();
            System.out.println("Server: user online");
            ServerReal serverReal = new ServerReal(clientSocket, list);
            list.add(serverReal);
            pool.execute(serverReal);

        }
    }
}
