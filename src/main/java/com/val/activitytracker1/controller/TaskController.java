package com.val.activitytracker1.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.val.activitytracker1.model.Task;
import com.val.activitytracker1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping( method = RequestMethod.GET)
    private String getAllTasks(Model model) {
        List<Task> list = taskService.loadAllTasks();
        model.addAttribute("allTasks", list);
        return "index";
    }

    @RequestMapping( path = "/taskByStatus", method = RequestMethod.GET)
    private String getTasksByStatus(@RequestParam("status")String status, Model model) {
        List<Task> list = taskService.loadTaskByStatus(status);
        model.addAttribute("allTasks", list);
        return "filterByStatus";
    }

    @RequestMapping( path = "/taskById", method = RequestMethod.GET)
    private String getTaskById(@RequestParam("id")Long id, Model model) {
        Task task = taskService.loadTaskById(id);
        model.addAttribute("allTasks", task);
        return "filterById";
    }


    @RequestMapping("/new")
    public String showNewTaskPage(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "add-task";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveNewTask(@ModelAttribute("task") Task task) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentTime = myDateObj.format(myFormatObj);
        task.setCreatedAt(currentTime);
        task.setStatus("pending");
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    private String editTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.loadTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }
    @GetMapping("/start/{id}")
    private String startTask(@PathVariable("id") Long id) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("in progress");
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/done/{id}")
    private String doneTask(@PathVariable("id") Long id) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("done");
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/pending/{id}")
    private String pendingTask(@PathVariable("id") Long id) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("pending");
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping(path = "/update/{id}/{status}/{createdAt}/{completedAt}", method = RequestMethod.POST)
    private String updateTask(@PathVariable("id") Long id,@PathVariable("status") String status,
                              @PathVariable("createdAt") String createdAt, @PathVariable("completedAt") String completedAt,
                              @ModelAttribute Task task) {

        task.setId(id);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentTime = myDateObj.format(myFormatObj);
        task.setUpdatedAt(currentTime);
        task.setStatus(status);
        task.setCreatedAt(createdAt);
        task.setCompletedAt(completedAt);

        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    private String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/filterByIdEdit/{id}")
    private String filterByIdEditTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.loadTaskById(id);
        model.addAttribute("task", task);
        return "filterByIdEdit-task";
    }
    @GetMapping("/filterByIdStart/{id}")
    private String filterByIdStartTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("in progress");
        List<Task> task1 = new ArrayList<>();
        task1.add(task);
        model.addAttribute("allTasks", task1);
        taskService.updateTask(task);

        return "filterById";
    }

    @GetMapping("/filterByIdDone/{id}")
    private String filterByIdDoneTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("done");
        List<Task> task1 = new ArrayList<>();
        task1.add(task);
        model.addAttribute("allTasks", task1);
        taskService.updateTask(task);
        return "filterById";
    }

    @GetMapping("/filterByIdPending/{id}")
    private String filterByIdPendingTask(@PathVariable("id") Long id,Model model) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("pending");
        List<Task> task1 = new ArrayList<>();
        task1.add(task);
        model.addAttribute("allTasks", task1);

        taskService.updateTask(task);
        return "filterById";
    }

    @RequestMapping(path = "/filterByIdUpdate/{id}/{status}/{createdAt}/{completedAt}", method = RequestMethod.POST)
    private String filterByIdUpdateTask(@PathVariable("id") Long id,@PathVariable("status") String status,
                              @PathVariable("createdAt") String createdAt, @PathVariable("completedAt") String completedAt,
                              @ModelAttribute Task task,Model model) {

        task.setId(id);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentTime = myDateObj.format(myFormatObj);
        task.setUpdatedAt(currentTime);
        task.setStatus(status);
        task.setCreatedAt(createdAt);
        task.setCompletedAt(completedAt);

        List<Task> task1 = new ArrayList<>();
        task1.add(task);
        model.addAttribute("allTasks", task1);

        taskService.updateTask(task);
        return "filterById";
    }

    @GetMapping("/filterByIdDelete/{id}")
    private String filterByIdDeleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "filterById";
    }
    @GetMapping("/filterByStatusEdit/{id}")
    private String filterByStatusEditTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.loadTaskById(id);
        model.addAttribute("task", task);
        return "filterByStatusEdit-task";
    }
    @GetMapping("/filterByStatusStart/{id}/{status}")
    private String filterByStatusStartTask(@PathVariable("id") Long id, @PathVariable("status") String status, Model model) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("in progress");
        taskService.updateTask(task);
        List<Task> task1 = taskService.loadTaskByStatus(status);
        model.addAttribute("allTasks", task1);

        return "filterByStatus";
    }

    @GetMapping("/filterByStatusDone/{id}/{status}")
    private String filterByStatusDoneTask(@PathVariable("id") Long id, @PathVariable("status") String status, Model model) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("done");
        taskService.updateTask(task);
        List<Task> task1 = taskService.loadTaskByStatus(status);
        model.addAttribute("allTasks", task1);
        return "filterByStatus";
    }

    @GetMapping("/filterByStatusPending/{id}/{status}")
    private String filterByStatusPendingTask(@PathVariable("id") Long id, @PathVariable("status") String status, Model model) {
        Task task = taskService.loadTaskById(id);
        task.setStatus("pending");
        taskService.updateTask(task);
        List<Task> task1 = taskService.loadTaskByStatus(status);
        model.addAttribute("allTasks", task1);

        return "filterByStatus";
    }

    @RequestMapping(path = "/filterByStatusUpdate/{id}/{status}/{createdAt}/{completedAt}", method = RequestMethod.POST)
    private String filterByStatusUpdateTask(@PathVariable("id") Long id,@PathVariable("status") String status,
                                    @PathVariable("createdAt") String createdAt, @PathVariable("completedAt") String completedAt,
                                    @ModelAttribute Task task,Model model) {

        task.setId(id);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentTime = myDateObj.format(myFormatObj);
        task.setUpdatedAt(currentTime);
        task.setStatus(status);
        task.setCreatedAt(createdAt);
        task.setCompletedAt(completedAt);

        taskService.updateTask(task);
        List<Task> task1 = taskService.loadTaskByStatus(status);
        model.addAttribute("allTasks", task1);

        return "filterByStatus";
    }

    @GetMapping("/filterByStatusDelete/{id}/{status}")
    private String filterDeleteTask(@PathVariable("id") Long id, Model model, @PathVariable("status") String status) {
        taskService.deleteTask(id);
        List<Task> task1 = taskService.loadTaskByStatus(status);
        model.addAttribute("allTasks", task1);

        return "filterByStatus";
    }
}