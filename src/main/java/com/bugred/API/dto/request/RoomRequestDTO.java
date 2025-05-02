package com.bugred.API.dto.request;

import com.bugred.API.enums.RoomStatus;

public class RoomRequestDTO {

    private String name;
    private RoomStatus status;

    public RoomRequestDTO() {}

    public RoomRequestDTO(String name, RoomStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomStatus status() {
        return status;
    }

    public RoomRequestDTO setStatus(RoomStatus status) {
        this.status = status;
        return this;
    }
}
