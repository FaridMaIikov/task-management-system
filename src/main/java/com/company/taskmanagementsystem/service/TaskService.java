package com.company.taskmanagementsystem.service;

import com.company.taskmanagementsystem.entity.Task;
import com.company.taskmanagementsystem.entity.User;
import com.company.taskmanagementsystem.exception.AlreadyExistsException;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.model.enums.EStatus;
import com.company.taskmanagementsystem.mapper.TaskMapper;
import com.company.taskmanagementsystem.model.request.TaskRequest;
import com.company.taskmanagementsystem.model.response.TaskResponse;
import com.company.taskmanagementsystem.repository.CategoryRepository;
import com.company.taskmanagementsystem.repository.TaskRepository;
import com.company.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EmailService emailService;

    private final TaskMapper taskMapper;

    public void createTask(Long id, TaskRequest taskRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found id " + id));

        categoryRepository.findById(taskRequest.getCategory().getId())
                .orElseThrow(() -> new NotFoundException("Category not found "));

        Task task = taskMapper.toEntity(taskRequest);
        task.setCreatedBy(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(EStatus.TODO.getValue());
        emailService.sendEmail(user.getEmail(), "Create Task", "Deadline " + task.getDeadline());
        taskRepository.save(task);
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found id " + id));

        return taskMapper.toTaskResponse(task);
    }

    public TaskResponse getTaskByName(String name) {
        Task task = taskRepository.findTaskByName(name)
                .orElseThrow(() -> new NotFoundException("Task not found name " + name));
        return taskMapper.toTaskResponse(task);
    }

    public List<TaskResponse> getTaskByStatus(String status) {
        List<Task> tasks = taskRepository.findTaskByStatus(status);
        return taskMapper.toTaskResponse(tasks);
    }


    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toTaskResponse(tasks);
    }

    public void changeStatus(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found " + id));
        if (!(task.getStatus().equals(EStatus.DONE.getValue()))) {
            task.setStatus(EStatus.DONE.getValue());
            taskRepository.save(task);
        } else {
            throw new AlreadyExistsException("Task status dont changed because status is DONE");
        }
    }
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found id " + id));
        taskRepository.delete(task);
    }

    public void deleteTaskByCategoryId(Long categoryId) {
        List<Task> tasks = taskRepository.getTaskByCategoryId(categoryId)
                .orElseThrow(() -> new NotFoundException("Task not found category id " + categoryId));
        taskRepository.deleteAll(tasks);
    }

}
