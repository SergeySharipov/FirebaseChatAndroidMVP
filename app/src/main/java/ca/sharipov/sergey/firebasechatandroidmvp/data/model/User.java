package ca.sharipov.sergey.firebasechatandroidmvp.data.model;

import java.util.Map;

public class User {
    private String username;
    private String email;
    private Map<String, Boolean> contactList;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getContactList() {
        return contactList;
    }

    public void setContactList(Map<String, Boolean> contactList) {
        this.contactList = contactList;
    }
}
