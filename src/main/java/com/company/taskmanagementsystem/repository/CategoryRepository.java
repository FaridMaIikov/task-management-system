package com.company.taskmanagementsystem.repository;

import com.company.taskmanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.name=:name  ")
    Optional<Category> findByCategoryName(String name);

}
