package ca.sharipov.sergey.firebasechatandroidmvp.data.model;

import java.util.Map;

public class Chat {
    private String chatName;
    private boolean isGroupChat;
    private Map<String, Boolean> members;

    public Chat(String chatName, boolean isGroupChat, Map<String, Boolean> members) {
        this.chatName = chatName;
        this.isGroupChat = isGroupChat;
        this.members = members;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public boolean isGroupChat() {
        return isGroupChat;
    }

    public void setGroupChat(boolean groupChat) {
        this.isGroupChat = groupChat;
    }

    public Map<String, Boolean> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Boolean> members) {
        this.members = members;
    }
}