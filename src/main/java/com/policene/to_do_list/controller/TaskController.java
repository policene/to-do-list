package com.policene.to_do_list.controller;

import com.policene.to_do_list.dto.request.TaskFilterDTO;
import com.policene.to_do_list.dto.request.TaskCreateDTO;
import com.policene.to_do_list.dto.request.TaskUpdateDTO;
import com.policene.to_do_list.dto.response.TaskResponseDTO;
import com.policene.to_do_list.dto.request.TaskUpdateStatusDTO;
import com.policene.to_do_list.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> save(@Valid @RequestBody TaskCreateDTO request) {
        return new ResponseEntity<>(taskService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateStatus (@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO request) {
        return new ResponseEntity<>(taskService.update(id, request), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponseDTO> updateStatus (@PathVariable Long id, @Valid @RequestBody TaskUpdateStatusDTO request) {
        return new ResponseEntity<>(taskService.updateStatus(id, request), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> listAll (
                @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                TaskFilterDTO filter
            ) {
        return new ResponseEntity<>(taskService.getActiveTasks(pageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById (@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
