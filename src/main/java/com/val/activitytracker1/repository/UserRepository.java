package com.val.activitytracker1.repository;

import com.val.activitytracker1.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

    public interface UserRepository extends CrudRepository<User, Long> {
        Optional<User> findByEmailAndPassword(String email, String password);


        Optional<User> findByEmail(String Email);


        long countByEmail(String email);

    }


