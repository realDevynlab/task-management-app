package com.devynlab.taskmanagement.service;

import com.devynlab.taskmanagement.dto.CreateTaskRequest;
import com.devynlab.taskmanagement.dto.TaskDto;
import com.devynlab.taskmanagement.dto.UpdateTaskRequest;
import com.devynlab.taskmanagement.entity.Task;
import com.devynlab.taskmanagement.exception.EntityNotFoundException;
import com.devynlab.taskmanagement.mapper.TaskMapper;
import com.devynlab.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAllNotDeleted();
        return taskMapper.toDtoList(tasks);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findByIdNotDeleted(id);
        if (task == null) throw new EntityNotFoundException("Task not found with id: " + id);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto createTask(CreateTaskRequest request) {
        Task task = taskMapper.toEntity(request);
        task = taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto updateTask(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findByIdNotDeleted(id);
        if (task == null) throw new EntityNotFoundException("Task not found with id: " + id);
        taskMapper.updateTaskFromDto(request, task);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findByIdNotDeleted(id);
        if (task == null) throw new EntityNotFoundException("Task not found with id: " + id);
        task.setDeleted(true);
        taskRepository.save(task);
    }

}
