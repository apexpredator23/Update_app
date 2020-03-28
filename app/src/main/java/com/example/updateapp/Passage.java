package com.example.updateapp;

import java.io.Serializable;

public class Passage implements Serializable {


    private String passage;
    private String Subject;
    private String id;
    public Passage() {
    }

    public Passage(String id, String passage, String subject) {
        this.id=id;
        this.passage = passage;
        Subject = subject;
    }



    public String getPassage() {
        return passage;
    }

    public void setPassage(String passage) {
        this.passage = passage;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

