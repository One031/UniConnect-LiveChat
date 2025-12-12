package com.mycompany.uniconnect.livechat.websocket;

import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

/**
 *
 * @author oneaz
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    
    // Default constructor
    public HttpSessionConfigurator() {
        super();
    }
    
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // Store the HttpSession from the HandshakeRequest into the endpoint's user properties.
        // This allows to retrieve the HttpSession later in the WebSocket endpoint (ChatEndpoint)
        sec.getUserProperties().put("httpSession", request.getHttpSession());
    }
}
