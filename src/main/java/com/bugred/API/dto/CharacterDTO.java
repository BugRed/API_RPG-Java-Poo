package com.bugred.API.dto;

import com.bugred.API.enums.LevelEnum;
import com.bugred.API.enums.ClassEnum;
import com.bugred.API.model.Character;

public class CharacterDTO {

    private int id;
    private String name;
    private String description;
    private StatusDTO status;
    private ClassEnum typeClass;
    private LevelEnum level;

    // Construtor padrão (necessário para serialização/deserialização)
    public CharacterDTO() {
    }

    // Construtor sem ID (útil para criação)
    public CharacterDTO(String name, String description, StatusDTO status, ClassEnum typeClass, LevelEnum level) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.typeClass = typeClass;
        this.level = level;
    }

    // Construtor completo com ID
    public CharacterDTO(int id, String name, String description, StatusDTO status, ClassEnum typeClass, LevelEnum level) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.typeClass = typeClass;
        this.level = level;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public CharacterDTO setId(int id) {
        this.id = id;
        return this;
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

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public ClassEnum getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(ClassEnum typeClass) {
        this.typeClass = typeClass;
    }

    public LevelEnum getlevel() {
        return level;
    }

    public CharacterDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    // Método utilitário para converter diretamente de um Character para CharacterDTO
    public static CharacterDTO fromCharacter(Character character) {
        return new CharacterDTO(
                character.getId(),
                character.getName(),
                character.getDescription(),
                StatusDTO.fromStatus(character.getStatus()),
                character.getTypeClass(),
                character.getLevel()
        );
    }
}