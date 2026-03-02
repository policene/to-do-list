package com.policene.to_do_list.dto.request;

import com.policene.to_do_list.domain.model.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record TaskCreateDTO(

        @NotBlank(message = "O título não pode ser vazio.")
        @Size(min = 3, max = 100, message = "O título deve possuir entre 3 a 100 caractéres.")
        String title,

        @Size(max = 500, message = "A descrição pode possuir no máximo 500 caractéres.")
        String description,

        @NotNull(message = "A prioridade é obrigatória.")
        TaskPriority priority

) {
}
