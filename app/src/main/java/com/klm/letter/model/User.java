package com.klm.letter.model;

public class User {
    private String name;
    private String email;
    private String uid;

    public User() {
        name = null;
        email = null;
        uid = null;
    }

    public User(String name, String email, String uid) {
        this.name = name;
        this.email = email;
        this.uid = uid;
    }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setUid(String uid) { this.uid = uid; }

    public String getName() {
        return name;
    }

    public String getUid() { return uid; }

    public String getEmail() { return email; }
}
