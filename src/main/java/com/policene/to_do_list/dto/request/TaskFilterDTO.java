package com.policene.to_do_list.dto.request;

import com.policene.to_do_list.domain.model.enums.TaskPriority;
import com.policene.to_do_list.domain.model.enums.TaskStatus;

public record TaskFilterDTO(
        TaskStatus status,
        TaskPriority priority
) {
}
