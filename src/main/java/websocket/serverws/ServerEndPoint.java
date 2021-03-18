package websocket.serverws;

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
    private final static List<Session> sessionList = new LinkedList<>();

    @OnWebSocketConnect
    public void onConnect(Session session){
        onText(session,"Someone connected!!!");
        this.session = session;
        sessionList.add(session);
        System.out.println("Количество подключенных юзеров: " + sessionList.size());
    }

    @OnWebSocketMessage
    public void onText(Session session,String msg){
        if(msg.equalsIgnoreCase("Exit")){
           session.close();
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
    public void onClose(Session session,int status,String reason){
        sessionList.remove(session);
        onText(session,"Someone disconnected!!");
    }

}
