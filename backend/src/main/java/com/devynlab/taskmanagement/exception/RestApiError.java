package com.devynlab.taskmanagement.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestApiError {

    private String code;

    private String message;

}
