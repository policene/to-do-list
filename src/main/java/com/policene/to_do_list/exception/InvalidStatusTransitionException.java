package com.policene.to_do_list.exception;

import com.policene.to_do_list.domain.model.enums.TaskStatus;

public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(TaskStatus original, TaskStatus request) {
        super("Não é possível alterar uma tarefa " + original + " para " + request + ".");
    }
}
