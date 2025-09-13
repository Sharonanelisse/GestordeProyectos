package com.smarroquin.gestordeproyectos.service;

import com.smarroquin.gestordeproyectos.model.Task;
import com.smarroquin.gestordeproyectos.repository.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped

public class TaskService {
    @Inject
    private TaskRepository repo;

    public List<Task> findByProject(Long projectId) {
        return repo.findByProject(projectId);
    }

    public void save(Task task) {
        if (task.getDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser pasada");
        }
        repo.save(task);
    }

    public void toggleDone(Task task) {
        task.setDone(!task.isDone());
        repo.save(task);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public boolean isOverdue(Task t) {
        return !t.isDone() && t.getDueDate().isBefore(LocalDate.now());
    }
}
