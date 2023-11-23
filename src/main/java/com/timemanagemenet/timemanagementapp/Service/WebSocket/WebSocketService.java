package com.timemanagemenet.timemanagementapp.Service.WebSocket;

import com.timemanagemenet.timemanagementapp.Entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.config.WebSocket.Greeting;

public interface WebSocketService {
    Greeting sendWebSocketMessage(WebSocketMessage message) throws Exception;
}
