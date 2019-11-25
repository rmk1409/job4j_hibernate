package ru.job4j.model;

import java.util.Calendar;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
