package zw.co.afrocodemy.afrocodemyjparelationships.service.project.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.Project;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto.ProjectRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto.ProjectResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.persistence.project.ProjectRepository;
import zw.co.afrocodemy.afrocodemyjparelationships.service.project.ProjectService;
import zw.co.afrocodemy.afrocodemyjparelationships.service.responsemappers.ResponseMapper;

@Data
@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        log.info("Creating a new project with name: {}", projectRequest.getName());

        Project project = Project.builder()
                .name(projectRequest.getName())
                .period(projectRequest.getPeriod())
                .build();

        log.debug("Built project entity: {}", project);
        Project savedProject = projectRepository.save(project);
        log.info("Project saved successfully with ID: {}", savedProject.getId());
        return ResponseMapper.mapToProjectResponse(savedProject);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        log.info("Fetching project with ID: {}", id);
        Project project = findById(id);
        log.debug("Found project: {}", project);
        return ResponseMapper.mapToProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        log.info("Updating project with ID: {}", id);
        Project project = findById(id);
        log.debug("Project before update: {}", project);

        project.setName(projectRequest.getName());
        project.setPeriod(projectRequest.getPeriod());
        log.debug("Project after update changes: {}", project);

        Project updatedProject = projectRepository.save(project);
        log.info("Project updated successfully with ID: {}", updatedProject.getId());
        return ResponseMapper.mapToProjectResponse(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        log.info("Deleting project with ID: {}", id);
        Project project = findById(id);
        log.debug("Project to be deleted: {}", project);
        projectRepository.delete(project);
        log.info("Project deleted successfully with ID: {}", id);
    }

    @Override
    public Page<ProjectResponse> getAllProjects(String searchParam, Pageable pageable) {
        log.info("Fetching all projects with search parameter: {}", searchParam);
        Page<Project> projectPage;

        if (searchParam != null && !searchParam.isEmpty()) {
            log.debug("Searching for projects with name containing: {}", searchParam);
            projectPage = projectRepository.findByNameContainingIgnoreCase(searchParam, pageable);
        } else {
            log.debug("Fetching all projects without filters");
            projectPage = projectRepository.findAll(pageable);
        }

        log.info("Fetched {} projects", projectPage.getTotalElements());
        return projectPage.map(ResponseMapper::mapToProjectResponse);
    }

    @Override
    public Project findById(Long id) {
        log.info("Finding project by ID: {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Project not found with ID: {}", id);
                    return new RuntimeException("Project not found with ID: " + id);
                });
        log.debug("Fetched project: {}", project);
        return project;
    }
}
