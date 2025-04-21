package com.bugred.API.dto;

import com.bugred.API.enums.LevelEnum;
import com.bugred.API.model.Status;
import com.bugred.API.enums.ClassEnum;

public class CharacterDTO {

    private String name;
    private String description;
    private Status status;
    private ClassEnum typeClass;
    private LevelEnum level;


    public CharacterDTO() {
    }

    public CharacterDTO(String name, String description, Status status, ClassEnum typeClass, LevelEnum level) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.typeClass = typeClass;
        this.level = level;
    }

    // Getters e setters
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ClassEnum getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(ClassEnum typeClass) {
        this.typeClass = typeClass;
    }

    public LevelEnum level() {
        return level;
    }

    public CharacterDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

}