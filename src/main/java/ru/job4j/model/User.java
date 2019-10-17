package ru.job4j.model;

import java.util.Calendar;

public class User {
    private long id;
    private String name;
    private Calendar expired;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String name, Calendar expired) {
        this.name = name;
        this.expired = expired;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpired() {
        return expired;
    }

    public void setExpired(Calendar expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name + '\''
                + ", expired=" + expired
                + '}';
    }
}
