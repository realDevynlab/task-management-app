package com.devynlab.taskmanagement.service;

import com.devynlab.taskmanagement.dto.CreateTaskRequest;
import com.devynlab.taskmanagement.dto.TaskDto;
import com.devynlab.taskmanagement.dto.UpdateTaskRequest;

import java.util.List;

public interface TaskService {

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Long id);

    TaskDto createTask(CreateTaskRequest request);

    TaskDto updateTask(Long id, UpdateTaskRequest request);

    void deleteTask(Long id);

}
