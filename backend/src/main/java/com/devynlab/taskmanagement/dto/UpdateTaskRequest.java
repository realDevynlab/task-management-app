package com.devynlab.taskmanagement.dto;

import lombok.Data;

@Data
public class UpdateTaskRequest {

    private String title; // No @NotBlank

    private String description;

    private Boolean completed;

}
