package com.devynlab.taskmanagement.mapper;

import com.devynlab.taskmanagement.dto.CreateTaskRequest;
import com.devynlab.taskmanagement.dto.TaskDto;
import com.devynlab.taskmanagement.dto.UpdateTaskRequest;
import com.devynlab.taskmanagement.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskDto toDto(Task task);

    List<TaskDto> toDtoList(List<Task> tasks);

    @Mapping(target = "completed", constant = "false")
    @Mapping(target = "deleted", constant = "false")
    Task toEntity(CreateTaskRequest request);

    void updateTaskFromDto(UpdateTaskRequest request, @MappingTarget Task task);

}
