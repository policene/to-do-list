package com.policene.to_do_list.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task não encontrada com o ID: " + id + ".");
    }
}
