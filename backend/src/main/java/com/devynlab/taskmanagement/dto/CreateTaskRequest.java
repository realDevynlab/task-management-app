package com.devynlab.taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

}
