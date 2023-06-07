package com.example.volley_api;

import android.net.Uri;

public class model_class
{
    String name;
    String email;
    String body;
    Uri uri;
    public model_class(String name, String email,String  body) {
        this.name = name;
        this.email = email;
        this.body=body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "model_class{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
