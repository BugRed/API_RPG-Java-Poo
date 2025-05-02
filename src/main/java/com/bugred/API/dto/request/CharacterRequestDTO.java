package com.bugred.API.dto.request;

import com.bugred.API.enums.LevelEnum;
import com.bugred.API.enums.ClassEnum;
import com.bugred.API.model.Character;

public class CharacterRequestDTO {

    private String name;
    private String description;
    private StatusRequestDTO status;
    private ClassEnum typeClass;
    private LevelEnum level;

    // Construtor padrão (necessário para serialização/deserialização)
    public CharacterRequestDTO() {
    }

    // Construtor sem ID (útil para criação)
    public CharacterRequestDTO(String name, String description, StatusRequestDTO status, ClassEnum typeClass, LevelEnum level) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.typeClass = typeClass;
        this.level = level;
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

    public StatusRequestDTO getStatus() {
        return status;
    }

    public void setStatus(StatusRequestDTO status) {
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

    public CharacterRequestDTO setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    // Método utilitário para converter diretamente de um Character para CharacterDTO
    public static CharacterRequestDTO fromCharacter(Character character) {
        return new CharacterRequestDTO(
                character.getName(),
                character.getDescription(),
                StatusRequestDTO.fromStatus(character.getStatus()),
                character.getTypeClass(),
                character.getLevel()
        );
    }
}