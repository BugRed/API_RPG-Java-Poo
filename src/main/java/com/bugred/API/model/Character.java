package com.bugred.API.model;

import com.bugred.API.dto.StatusDTO;
import com.bugred.API.enums.ClassEnum;
import com.bugred.API.enums.LevelEnum;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Objects;

public class Character {

    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("status")
    private Status status;

    @SerializedName("typeClass")
    private ClassEnum typeClass;

    @SerializedName("level")
    private LevelEnum level;

    // Construtores
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

    // Métodos getter e setter com fluent interface
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

    public LevelEnum getLevel() {
        return level;
    }

    public Character setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    // Métodos equals e hashCode
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

    // Método toString
    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", typeClass=" + typeClass +
                ", level=" + level +
                '}';
    }

}
