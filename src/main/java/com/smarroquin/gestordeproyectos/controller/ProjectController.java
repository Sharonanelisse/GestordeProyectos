package com.smarroquin.gestordeproyectos.controller;

import com.smarroquin.gestordeproyectos.service.ProjectService;
import com.smarroquin.gestordeproyectos.model.Project;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class ProjectController implements Serializable {
    @Inject
    private ProjectService service;

    private Project selected = new Project();

    public List<Project> getProjects() {
        return service.findAll();
    }

    public void newProject() {
        selected = new Project();
    }

    public void save() {
        service.save(selected);
        selected = new Project();
    }

    public void delete(Project project) {
        service.delete(project.getId());
    }

    public Project getSelected() {
        return selected;
    }

    public void setSelected(Project selected) {
        this.selected = selected;
    }
}
