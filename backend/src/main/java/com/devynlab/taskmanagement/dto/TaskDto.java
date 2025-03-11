package com.devynlab.taskmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Task DTO")
public class TaskDto {

    @Schema(description = "Task ID", example = "1")
    private Long id;

    @Schema(description = "Task title", example = "Buy groceries")
    private String title;

    @Schema(description = "Task description", example = "Milk, eggs, bread")
    private String description;

    @Schema(description = "Task completion status", example = "false")
    private boolean completed;
}
