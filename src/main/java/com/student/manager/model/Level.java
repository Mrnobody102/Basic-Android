package com.student.manager.model;

public class Level {
    int level_id;
    String name;
    String description;

    public Level(int level_id, String name, String description) {
        this.level_id = level_id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    @Override
    public String toString() {
        return name;

    }
}
