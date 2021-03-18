package websocket.serverws;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9080);
        HandlerList handlerList = new HandlerList();
        server.setHandler(handlerList);
        handlerList.addHandler(new WebSocketProvider());
        server.start();
        server.join();
    }
}
