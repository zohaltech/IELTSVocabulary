package com.zohaltech.app.ieltsvocabulary.entities;

import java.io.Serializable;

public class Theme implements Serializable {
    private int    id;
    private int    level;
    private String name;
    private String iconName;

    public Theme(int id, int level, String name, String iconName) {
        this(level, name, iconName);
        this.id = id;
    }

    public Theme(int level, String name, String iconName) {
        setLevel(level);
        setName(name);
        setIconName(iconName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
