package com.timemanagemenet.timemanagementapp.service.webSocket;

import com.timemanagemenet.timemanagementapp.entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.config.webSocket.Greeting;

public interface WebSocketService {
    Greeting sendWebSocketMessage(WebSocketMessage message) throws Exception;
}
