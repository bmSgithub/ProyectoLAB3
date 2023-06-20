package com.mygdx.game.Users;

import java.util.List;
import java.util.Objects;

public class User {
    private static int lastAssignedId = 0;
    private int id;
    private String username;
    private int points;

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public static int generateNextId() {
        return ++lastAssignedId;
    }

    //endregion


    public User(String username, int points) {
        this.id = generateNextId();
        this.username = username;
        this.points = points;
    }

    public User(){}
    public static void updateLastAssignedId(List<User> userList) {
        for (User user : userList) {
            if (user.getId() > lastAssignedId) {
                lastAssignedId = user.getId();
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
