package com.bugred.API.model;

import com.bugred.API.enums.ClassEnum;
import com.bugred.API.enums.LevelEnum;

import java.util.Objects;

public class Character {

    private int id;
    private String name;
    private String description;
    private Status status;
    private ClassEnum typeClass;
    private LevelEnum level;

    public Character() {
    }

    public Character(int id, String name, String description, Status status, ClassEnum typeClass, LevelEnum level) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.typeClass = typeClass;
        this.level = level;
    }

    // Métodos getter e setter
    public int getId() {
        return id;
    }

    public Character setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Character setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Character setDescription(String description) {
        this.description = description;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Character setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ClassEnum getTypeClass() {
        return typeClass;
    }

    public Character setTypeClass(ClassEnum typeClass) {
        this.typeClass = typeClass;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", typeClass=" + typeClass +
                '}';
    }
}
