package websocket.serverws;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import websocket.serverws.ServerEndPoint;

public class WebSocketProvider extends WebSocketHandler {
    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(ServerEndPoint.class);
    }
}
