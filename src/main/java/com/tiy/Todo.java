package com.tiy;

import javax.persistence.*;

/**
 * Created by fenji on 9/20/2016.
 */
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    String done;

    @ManyToOne
    User user;

    public Todo() {
    }

    public Todo(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

