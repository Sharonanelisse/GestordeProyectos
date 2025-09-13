package com.smarroquin.gestordeproyectos.repository;

import com.smarroquin.gestordeproyectos.model.Task;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped

public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private long counter = 1;

    @PostConstruct
    public void init() {
        Task t1 = new Task();
        t1.setId(counter++);
        t1.setProjectId(1L);
        t1.setTitle("Diseñar base de datos");
        t1.setPriority(Task.Priority.HIGH);
        t1.setDueDate(LocalDate.now().plusDays(5));
        t1.setDone(false);
        tasks.add(t1);
    }

    public List<Task> findByProject(Long projectId) {
        return tasks.stream()
                .filter(t -> t.getProjectId().equals(projectId))
                .collect(Collectors.toList());
    }

    public void save(Task task) {
        if (task.getId() == null) {
            task.setId(counter++);
            tasks.add(task);
        } else {
            deleteById(task.getId());
            tasks.add(task);
        }
    }

    public void deleteById(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }
}
