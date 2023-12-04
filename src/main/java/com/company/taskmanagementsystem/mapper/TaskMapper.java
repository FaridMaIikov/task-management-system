package com.company.taskmanagementsystem.mapper;

import com.company.taskmanagementsystem.entity.Task;
import com.company.taskmanagementsystem.model.request.TaskRequest;
import com.company.taskmanagementsystem.model.response.TaskResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskRequest taskRequest);

    List<TaskResponse> toTaskResponse(List<Task> tasks);

    TaskResponse toTaskResponse(Task task);

}
