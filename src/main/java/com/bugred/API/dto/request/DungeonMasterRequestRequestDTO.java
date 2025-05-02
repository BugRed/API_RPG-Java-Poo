package com.bugred.API.dto.request;

import java.util.List;

public class DungeonMasterRequestRequestDTO extends UserRequestDTO {

        private List<CharacterRequestDTO> characters;
        private List<RoomRequestDTO> rooms;

        public DungeonMasterRequestRequestDTO(String username, String email) {
            super(username, email);
        }

        public DungeonMasterRequestRequestDTO() {
        }

        public List<CharacterRequestDTO> getCharacters() {
            return characters;
        }

        public List<RoomRequestDTO> getRooms() {
            return rooms;
        }

        @Override
        public String toString() {
            return "DungeonMaster{" +
                    "characters=" + characters +
                    ", rooms=" + rooms +
                    '}';
        }

}
