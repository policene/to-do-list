package com.policene.to_do_list.dto.request;

import com.policene.to_do_list.domain.model.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;


public record TaskUpdateStatusDTO(
        @NotNull(message = "O status é obrigatório.")
        TaskStatus status
) {
}
