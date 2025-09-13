package com.smarroquin.gestordeproyectos.repository;

import com.smarroquin.gestordeproyectos.model.Project;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped

public class ProjectRepository {

    private final List<Project> projects = new ArrayList<>();
    private long counter = 1;

    @PostConstruct
    public void init() {
        Project p1 = new Project();
        p1.setId(counter++);
        p1.setName("Sistema Académico");
        p1.setOwner("Sharon");
        p1.setStatus(Project.Status.ACTIVE);
        p1.setCreatedAt(LocalDateTime.now());
        projects.add(p1);
    }

    public List<Project> findAll() {
        return projects;
    }

    public Optional<Project> findById(Long id) {
        return projects.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void save(Project project) {
        if (project.getId() == null) {
            project.setId(counter++);
            project.setCreatedAt(LocalDateTime.now());
            projects.add(project);
        } else {
            deleteById(project.getId());
            projects.add(project);
        }
    }

    public void deleteById(Long id) {
        projects.removeIf(p -> p.getId().equals(id));
    }
}
