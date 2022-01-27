package com.example.tedc.models;

public class Post {
    String postId;
    String posertId;
    String tittle;
    String description;
    String image;

    public Post() {
    }

    public Post(String tittle, String description, String image) {
        this.tittle = tittle;
        this.description = description;
        this.image = image;

    }


    public String getPosertId() {
        return posertId;
    }

    public void setPosertId(String posertId) {
        this.posertId = posertId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
