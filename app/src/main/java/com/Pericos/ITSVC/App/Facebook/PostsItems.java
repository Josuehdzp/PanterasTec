package com.Pericos.ITSVC.App.Facebook;

public class PostsItems {
    private String name;
    private String created_time;
    private String full_picture;
    private String message;
    private String link;


    public String getName() {
        return name;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getFull_picture() {
        return full_picture;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public PostsItems (String name, String created_time, String message, String permalink_url, String full_picture){

        this.name = name;
        this.created_time = created_time;
        this.message = message;
        this.full_picture = full_picture;
        this.link = permalink_url;

    }
}
