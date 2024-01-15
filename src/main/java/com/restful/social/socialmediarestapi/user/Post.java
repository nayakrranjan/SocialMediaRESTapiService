package com.restful.social.socialmediarestapi.user;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;

    private String tweet;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", tweet='" + tweet + '\'' +
                '}';
    }
}
