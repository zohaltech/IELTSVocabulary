package com.zohaltech.app.ieltsvocabulary.entities;

import java.io.Serializable;

public class Theme implements Serializable {
    private int    id;
    private String name;


    public Theme(int id,  String name) {
        this( name);
        this.id = id;
    }

    public Theme( String name) {

        setName(name);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
