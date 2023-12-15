package com.timemanagemenet.timemanagementapp.Entity;

public class WebSocketMessage {

    private String socketMessage;
    private long objectId;

    private String taskId;
    public WebSocketMessage() {
    }
    public WebSocketMessage(String socketMessage, long id) {
        this.socketMessage = socketMessage;
        this.objectId = id;
    }

    public WebSocketMessage(String socketMessage, String taskId) {
        this.socketMessage = socketMessage;
        this.taskId = taskId;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public WebSocketMessage(String socketMessage) {
        this.socketMessage = socketMessage;
    }

    public String getSocketMessage() {
        return socketMessage;
    }

    public void setSocketMessage(String socketMessage) {
        this.socketMessage = socketMessage;
    }
}