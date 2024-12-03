package zw.co.afrocodemy.afrocodemyjparelationships.api.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto.ProjectRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto.ProjectResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.service.project.ProjectService;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@SecurityRequirement(name = "authorization")
@Tag(name = "Project Controller", description = "APIs for managing projects, including CRUD operations and pagination")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Create a new project", description = "Adds a new project to the database.")
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @RequestBody @Valid ProjectRequest projectRequest) {
        log.info("Creating project with name: {}", projectRequest.getName());
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @Operation(summary = "Get project by ID", description = "Fetches a project by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(
            @PathVariable @Parameter(description = "Unique ID of the project") Long id) {
        log.info("Fetching project with ID: {}", id);
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @Operation(summary = "Update a project", description = "Updates details of an existing project.")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable @Parameter(description = "Unique ID of the project") Long id,
            @RequestBody @Valid ProjectRequest projectRequest) {
        log.info("Updating project with ID: {}", id);
        return ResponseEntity.ok(projectService.updateProject(id, projectRequest));
    }

    @Operation(summary = "Delete a project", description = "Deletes a project from the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable @Parameter(description = "Unique ID of the project") Long id) {
        log.info("Deleting project with ID: {}", id);
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all projects", description = "Fetches a paginated list of all projects.")
    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> getAllProjects(
            @RequestParam(required = false) @Parameter(description = "Search parameter (optional)") String searchParam,
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Page size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        log.info("Fetching projects with searchParam: {}", searchParam);
        return ResponseEntity.ok(projectService.getAllProjects(searchParam, pageable));
    }
}
