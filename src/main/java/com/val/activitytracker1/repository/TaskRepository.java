package com.val.activitytracker1.repository;


import com.val.activitytracker1.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
}
