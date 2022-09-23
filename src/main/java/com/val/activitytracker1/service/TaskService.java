package com.val.activitytracker1.service;


import java.util.List;

import com.val.activitytracker1.model.Task;
import com.val.activitytracker1.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<Task> loadAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task loadTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    public List<Task> loadTaskByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public Task saveTask(Task task) {
        taskRepository.save(task);
        return loadTaskById(task.getId());
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

}