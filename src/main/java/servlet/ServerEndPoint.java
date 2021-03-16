package servlet;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@WebSocket
public class ServerEndPoint {
    Session session = null;
    private List<Session> sessionList = new LinkedList<>();
    @OnWebSocketConnect
    public void getConnect(Session session){
        this.session = session;
        sessionList.add(session);

    }
    @OnWebSocketMessage
    public void getMessages(Session session,String msg){
        if(msg.equalsIgnoreCase("Exit")){
            getClose(session,1000,"Потому что могу");
        }else {
            sessionList.forEach(s -> {
                if(s == this.session){
                    try {
                        s.getRemote().sendString(msg.toUpperCase(Locale.ROOT));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        s.getRemote().sendString(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
    @OnWebSocketClose
    public void getClose(Session session,int status,String reason){
        sessionList.remove(session);
    }
}
