package servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        HandlerList handlerList = new HandlerList();
        server.setHandler(handlerList);
        handlerList.addHandler(Container.getServletHandler());

        server.start();
        server.join();

    }
}