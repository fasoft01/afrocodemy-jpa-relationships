package zw.co.afrocodemy.afrocodemyjparelationships.service.project;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.Project;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto.ProjectRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto.ProjectResponse;

public interface ProjectService {
    ProjectResponse createProject(@Valid ProjectRequest projectRequest);

    ProjectResponse getProjectById(Long id);

    ProjectResponse updateProject(Long id, @Valid ProjectRequest projectRequest);

    void deleteProject(Long id);

    Page<ProjectResponse> getAllProjects(String searchParam, Pageable pageable);

    Project findById(Long id);
}
