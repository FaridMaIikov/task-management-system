package com.company.taskmanagementsystem.repository;

import com.company.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("select t from Task t where t.status=:status")
    List<Task> findTaskByStatus(String status);

    @Query("select t from Task t where t.name=:name")
    Optional<Task> findTaskByName(String name);

    @Query("select t from Task t where t.category.id=:categoryId")
    Optional<List<Task>> getTaskByCategoryId(Long categoryId);

}
