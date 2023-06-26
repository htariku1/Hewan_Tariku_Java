package com.company.chatterbook;

import java.util.List;
import java.util.ArrayList;
public class User {
    private String name;
    private List<ChatterPost> chatterPosts;

    public User(String name) {
        this.name = name;
        this.chatterPosts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }
    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }
}
