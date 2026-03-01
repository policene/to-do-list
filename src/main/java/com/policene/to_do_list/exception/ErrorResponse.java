package com.policene.to_do_list.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class ErrorResponse {

    private final String error;
    private final String message;
    private final int status;
    private final String url;
    private final Instant timestamp;

    public ErrorResponse (HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        this.error = httpStatus.getReasonPhrase();
        this.message = e.getMessage();
        this.url = request.getRequestURI();
        this.status = httpStatus.value();
        this.timestamp = Instant.now();
    }


}
