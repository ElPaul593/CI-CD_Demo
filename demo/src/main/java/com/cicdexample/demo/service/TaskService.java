package com.cicdexample.demo.service;

import com.cicdexample.demo.exception.TaskNotFoundException;
import com.cicdexample.demo.model.Task;
import com.cicdexample.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Crear
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    // Listar todas
    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    // Obtener por id
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // Actualizar
    public Task update(Long id, Task taskDetails) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        existing.setTitle(taskDetails.getTitle());
        existing.setDescription(taskDetails.getDescription());
        existing.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(existing);
    }

    // Eliminar
    public void delete(Long id) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(existing);
    }
}
