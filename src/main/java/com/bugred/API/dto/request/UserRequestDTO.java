package com.bugred.API.dto.request;

public class UserRequestDTO {

        private String username;
        private String email;

        public UserRequestDTO(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public UserRequestDTO() {
        }

        public String getUsername() { return username; }
        public String getEmail() { return email; }

        public void setUsername(String username) { this.username = username; }
        public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

