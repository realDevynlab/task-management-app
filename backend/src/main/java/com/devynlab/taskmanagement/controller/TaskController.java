package com.devynlab.taskmanagement.controller;

import com.devynlab.taskmanagement.dto.CreateTaskRequest;
import com.devynlab.taskmanagement.dto.TaskDto;
import com.devynlab.taskmanagement.dto.UpdateTaskRequest;
import com.devynlab.taskmanagement.exception.RestApiResponse;
import com.devynlab.taskmanagement.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tasks")
@Tag(name = "Tasks", description = "Operations related to tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<List<TaskDto>>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        RestApiResponse<List<TaskDto>> response = RestApiResponse.<List<TaskDto>>builder()
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Successfully retrieved tasks")
                .timestamp(LocalDateTime.now())
                .count(tasks.size())
                .data(tasks)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<TaskDto>> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        RestApiResponse<TaskDto> response = RestApiResponse.<TaskDto>builder()
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Successfully retrieved task")
                .timestamp(LocalDateTime.now())
                .data(task)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RestApiResponse<TaskDto>> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskDto createdTask = taskService.createTask(request);
        RestApiResponse<TaskDto> response = RestApiResponse.<TaskDto>builder()
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .message("Task created successfully")
                .timestamp(LocalDateTime.now())
                .data(createdTask)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/api/tasks/" + createdTask.getId())
                .body(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<RestApiResponse<TaskDto>> partialUpdateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {
        TaskDto updatedTask = taskService.updateTask(id, request);
        RestApiResponse<TaskDto> response = RestApiResponse.<TaskDto>builder()
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Task updated successfully")
                .timestamp(LocalDateTime.now())
                .data(updatedTask)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RestApiResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        RestApiResponse<Void> response = RestApiResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("Task deleted successfully")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
