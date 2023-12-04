package com.company.taskmanagementsystem.controller;

import com.company.taskmanagementsystem.model.request.TaskRequest;
import com.company.taskmanagementsystem.model.response.TaskResponse;
import com.company.taskmanagementsystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public void createTask(@RequestParam("user-id") Long id,
                           @RequestBody TaskRequest taskRequest) {
        taskService.createTask(id,taskRequest);
    }


    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/name")
    public TaskResponse getTaskByName(@RequestParam String name) {
        return taskService.getTaskByName(name);
    }

    @GetMapping("/status")
    public List<TaskResponse> getTaskByStatus(@RequestParam String status) {
        return taskService.getTaskByStatus(status);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @PatchMapping("/update/change-status/{id}")
    public void changeStatus(@PathVariable Long id){
        taskService.changeStatus(id);
    }
}
