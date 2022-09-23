package com.val.activitytracker1.model;

import javax.persistence.*;

    @Entity(name ="users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long userId;
        @Column(name = "username")
        private String username;
        @Column(name = "email")
        private String email;
        @Column(name = "role")
        private String role;
        @Column(name = "password")
        private String password;

        public User() {
        }

        public User(Long userId, String username, String email, String role, String password) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.role = role;
            this.password = password;
        }

        public User(String username, String email, String role, String password) {
            this.username = username;
            this.email = email;
            this.role = role;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long user_id) {
            this.userId = user_id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


