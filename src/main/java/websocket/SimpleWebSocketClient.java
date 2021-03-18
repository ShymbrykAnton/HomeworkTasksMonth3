package websocket;

import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import javax.websocket.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

@ClientEndpoint
public class SimpleWebSocketClient {
   static Session userSession = null;

   public SimpleWebSocketClient(URI endpointURI){
       try {
           WebSocketContainer container = ContainerProvider.getWebSocketContainer();
           container.connectToServer(this, endpointURI);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }
    @OnMessage
    public void onMessage(Session session,String message){
        System.out.println(message);
    }

    @OnClose
    public void onClose(Session session){

    }

    public static void main(String[] args) throws URISyntaxException {
        SimpleWebSocketClient simpleWebSocketClient =
                new SimpleWebSocketClient(new URI("ws://localhost:9080/"));
        while (true) {
            Scanner scanner = new Scanner(System.in);
            userSession.getAsyncRemote().sendText(scanner.nextLine());
        }
    }
}
