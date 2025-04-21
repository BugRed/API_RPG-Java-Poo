package com.bugred.API.model;

import com.bugred.API.enums.LevelEnum;

import java.util.Objects;


public class Status {

    private int id;

    private String characterName;
    private LevelEnum characterLevel;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;


    public Status() {
    }

    public Status(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public Status(String active) {
    }

    public int getId() {
        return id;
    }

    public Status setId(int id) {
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

    public String characterName() {
        return characterName;
    }

    public Status setCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public LevelEnum characterLevel() {
        return characterLevel;
    }

    public Status setCharacterLevel(LevelEnum characterLevel) {
        this.characterLevel = characterLevel;
        return this;
    }

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