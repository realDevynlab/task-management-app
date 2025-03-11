package com.devynlab.taskmanagement.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiResponse<T> {

    private HttpStatus status;

    private int statusCode;

    private String message;

    private LocalDateTime timestamp;

    private int count;

    private T data;

    private List<RestApiError> errors;

    private String path;

}
