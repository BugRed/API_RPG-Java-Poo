package com.bugred.API.model;

import com.bugred.API.enums.RoomStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {
    private UUID id;
    private String name;
    private List<Character> participants;
    private RoomStatus status;

    public Room(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.participants = new ArrayList<>();
        this.status = RoomStatus.ABERTA;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Character> getParticipants() {
        return participants;
    }

    public RoomStatus getStatus() {
        return status;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    // Lógica adicional
    public void addParticipant(Character character) {
        participants.add(character);
    }

    // todo Lembrar de implementar logica de sala cheia um dia
    public boolean isFull(int maxParticipants) {
        return participants.size() >= maxParticipants;
    }
}

