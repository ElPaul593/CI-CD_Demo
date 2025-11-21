package com.cicdexample.demo.controller;

import com.cicdexample.demo.model.Task;
import com.cicdexample.demo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // si luego tienes front, así evitas problemas de CORS básicos
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Crear tarea
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task created = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Listar todas las tareas
    @GetMapping
    public List<Task> listAll() {
        return taskService.listAll();
    }

    // Obtener una tarea por id
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    // Actualizar tarea
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
