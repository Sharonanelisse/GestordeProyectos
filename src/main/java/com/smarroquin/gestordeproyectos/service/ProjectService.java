package com.smarroquin.gestordeproyectos.service;

import com.smarroquin.gestordeproyectos.model.Project;
import com.smarroquin.gestordeproyectos.repository.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProjectService {
    @Inject
    private ProjectRepository repo;

    public List<Project> findAll() {
        return repo.findAll();
    }

    public void save(Project project) {
        if (isNameDuplicated(project)) {
            throw new IllegalArgumentException("Nombre duplicado");
        }
        repo.save(project);
    }

    private boolean isNameDuplicated(Project project) {
        return repo.findAll().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(project.getName())
                        && !p.getId().equals(project.getId()));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
