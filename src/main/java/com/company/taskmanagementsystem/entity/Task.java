package com.company.taskmanagementsystem.entity;

import com.company.taskmanagementsystem.model.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Category category;

    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    User createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "start_time")
    LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate deadline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    User modifiedBy;

    @Column(columnDefinition = "TODO")
    String status;

    String description;
}
