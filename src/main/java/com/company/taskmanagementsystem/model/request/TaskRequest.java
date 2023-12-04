package com.company.taskmanagementsystem.model.request;

import com.company.taskmanagementsystem.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {
    @NotBlank
    String name;

    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    LocalDate deadline;

    @NotBlank
    String description;

    @NotBlank
    Category category;

    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    LocalDate startTime;
}
