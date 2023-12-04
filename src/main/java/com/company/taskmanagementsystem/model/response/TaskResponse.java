package com.company.taskmanagementsystem.model.response;

import com.company.taskmanagementsystem.entity.Category;
import com.company.taskmanagementsystem.entity.User;
import com.company.taskmanagementsystem.model.enums.EStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(

        Category category,
        String name,
        User createdBy,
        LocalDateTime createdAt,
        LocalDateTime startTime,
        LocalDate deadline,
        LocalDateTime updatedAt,
        User modifiedBy,
        EStatus status,
        String description
) {
}
