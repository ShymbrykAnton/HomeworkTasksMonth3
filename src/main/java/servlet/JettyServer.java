package servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

public class JettyServer {
    private final static int PORT_NUMBER = 8080;

    public static void main(String[] args) throws Exception {
        Server server = new Server(PORT_NUMBER);
        HandlerList handlerList = new HandlerList();
        server.setHandler(handlerList);
        handlerList.addHandler(Container.getServletHandler());
        server.start();
        server.join();
    }
}