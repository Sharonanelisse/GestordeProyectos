package com.smarroquin.gestordeproyectos.controller;

import com.smarroquin.gestordeproyectos.service.TaskService;
import com.smarroquin.gestordeproyectos.model.Task;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class TaskCrontoller implements Serializable {
    @Inject
    private TaskService service;

    private Long currentProjectId;
    private Task selected = new Task();

    public List<Task> getTasks() {
        if (currentProjectId == null) return List.of();
        return service.findByProject(currentProjectId);
    }

    public void loadTasks(Long projectId) {
        this.currentProjectId = projectId;
    }

    public void newTask(Long projectId) {
        selected = new Task();
        selected.setProjectId(projectId);
    }

    public void save() {
        service.save(selected);
        selected = new Task();
    }

    public void toggleDone(Task task) {
        service.toggleDone(task);
    }

    public void delete(Task task) {
        service.delete(task.getId());
    }

    public boolean isOverdue(Task task) {
        return service.isOverdue(task);
    }

    public Task getSelected() {
        return selected;
    }

    public void setSelected(Task selected) {
        this.selected = selected;
    }
}
