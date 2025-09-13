package com.smarroquin.gestordeproyectos.model;
import java.time.LocalDate;

public class Task {
    private Long id;
    private Long projectId;
    private String title;
    private Priority priority;
    private LocalDate dueDate;
    private boolean done;
    private String notes;

    public enum Priority { LOW, MEDIUM, HIGH }

    public Task() {}
    public Task(Long projectId, String title, Priority priority, LocalDate dueDate, boolean done) {
        this.projectId = projectId;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.done = done;
        this.notes = null;
    }

    public Long getId() {
            return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
