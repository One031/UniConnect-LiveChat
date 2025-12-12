package com.mycompany.uniconnect.livechat.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;



@ServerEndpoint(value = "/chatEndpoint", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {
    
    // Thread-safe set of all active WebSocket sessions (connected clients)
    private static final Set<Session> sessions = ConcurrentHashMap.newKeySet();
    
    // Map each session to its corresponding username
    private static final ConcurrentHashMap<Session, String> usernames = new ConcurrentHashMap<>();
    
    @OnOpen
    public void onOpen (Session session, jakarta.websocket.EndpointConfig config) {
        // Get HttpSession from user properties
        jakarta.servlet.http.HttpSession httpSession = (jakarta.servlet.http.HttpSession) config.getUserProperties().get("httpSession");
        
        // If user info exists in HttpSession, use it as username; otherwise assign "Guest"
        String username = (httpSession != null && httpSession.getAttribute("user") != null) 
            ? (String) httpSession.getAttribute("user") : "Guest";

        // Store the username associated with the WebSocket session    
        usernames.put(session, username);
        
        // Add the session to the active sessions set 
        sessions.add(session);
        
        System.out.println(username + " connected."); // Debug log
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        // Ignore empty or null messages
        if (message == null || message.trim().isEmpty()) return;
        
        // Retrieve the username for the sender
        String username = usernames.getOrDefault(session, "Guest");
        
        // Broadcast the message to all connected clients
        broadcast(username + ": " + message);
    }
    
    @OnClose
    public void onClose(Session session) {
        // Get the username of the disconnected user
        String username = usernames.get(session);
        
        // Remove session from active sessions and user mapping
        sessions.remove(session);
        usernames.remove(session);
        
        System.out.println(username +" disconnected");
    }
    
    private void broadcast(String message) {
        for (Session s : sessions) {
            if (s.isOpen()) { // Ensure the session is still open
                try {
                    // Send the message to the client
                    s.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace(); // Log any errors while sending
                }
            }
        }
    }
}