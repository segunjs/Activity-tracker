package com.val.activitytracker1.service;

import com.val.activitytracker1.model.User;
import com.val.activitytracker1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class UserService {

        private final UserRepository userRepository;
        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public Long validEmail(String email){
            return userRepository.countByEmail(email);
        }
        public User getUser(String email, String password){
            return userRepository.findByEmailAndPassword(email, password).orElse(null);
        }

        public void adduser(User user){
            userRepository.save(user);
        }

    }


