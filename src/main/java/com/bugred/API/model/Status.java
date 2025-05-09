package com.bugred.API.model;

import com.bugred.API.enums.LevelEnum;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

public class Status {

    private UUID id;

    @SerializedName("characterName")
    private String characterName;

    @SerializedName("characterLevel")
    private LevelEnum characterLevel;


    // todo SERIALIZAR
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    // Construtor padrão
    public Status() {
    }

    // Construtor para inicializar os atributos de status
    public Status(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    // Construtor para inicializar com um status ativo (poderia ser útil dependendo do seu caso)
    public Status(String active) {
        // Inicializar com base no parâmetro "active" (exemplo de como pode ser usado)
    }

    // Métodos getter e setter
    public UUID getId() {
        return id;
    }

    public Status setId(UUID id) {
        this.id = id;
        return this;
    }

    public int getStrength() {
        return strength;
    }

    public Status setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public int getDexterity() {
        return dexterity;
    }

    public Status setDexterity(int dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public int getConstitution() {
        return constitution;
    }

    public Status setConstitution(int constitution) {
        this.constitution = constitution;
        return this;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public Status setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public int getWisdom() {
        return wisdom;
    }

    public Status setWisdom(int wisdom) {
        this.wisdom = wisdom;
        return this;
    }

    public int getCharisma() {
        return charisma;
    }

    public Status setCharisma(int charisma) {
        this.charisma = charisma;
        return this;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Status setCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public LevelEnum getCharacterLevel() {
        return characterLevel;
    }

    public Status setCharacterLevel(LevelEnum characterLevel) {
        this.characterLevel = characterLevel;
        return this;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return id == status.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Método toString
    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", characterName='" + characterName + '\'' +
                ", characterLevel=" + characterLevel +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                '}';
    }
}
