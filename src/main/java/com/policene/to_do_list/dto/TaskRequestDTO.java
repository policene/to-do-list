package com.policene.to_do_list.dto;

import com.policene.to_do_list.domain.model.enums.TaskPriority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record TaskRequestDTO(

        @NotBlank @Size(min = 3, max = 100)
        String title,

        @NotBlank @Size(max = 500)
        String description,

        TaskPriority priority

) {
}
