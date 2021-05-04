package com.squirrel.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import static com.squirrel.websocket.utils.WebSocketUtils.ONLINE_USER_SESSIONS;
import static com.squirrel.websocket.utils.WebSocketUtils.sendMessageAll;


@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.put(username, session);
        String message = "Welcome[" + username + "] to the Chat Room";
        LOGGER.info("User log in: " + message);
        sendMessageAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        LOGGER.info("Send Message: " + message);
        sendMessageAll("User[" + username + "]:" + message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.remove(username);
        sendMessageAll("User[" + username + "] has left.");
        try {
            session.close();
        } catch (IOException e) {
            LOGGER.error("onClose error", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            LOGGER.error("onError exception", e);
        }
        LOGGER.info("Throwable msg " + throwable.getMessage());
    }
}
