package com.zohaltech.app.ieltsvocabulary.entities;


public class Sentence {
    private int id;
    private String text;


    public Sentence(int id, String text) {
        this(text);
        this.id = id;
    }

    public Sentence(String text) {
        setText(text);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
