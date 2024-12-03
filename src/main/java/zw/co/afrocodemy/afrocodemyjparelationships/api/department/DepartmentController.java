package zw.co.afrocodemy.afrocodemyjparelationships.api.department;

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
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto.DepartmentRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto.DepartmentResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.service.department.DepartmentService;

@Slf4j
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@SecurityRequirement(name = "authorization")
@Tag(name = "Department Controller", description = "APIs for managing departments, including CRUD operations and pagination")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Create a new department", description = "Adds a new department to the database.")
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @RequestBody @Valid DepartmentRequest departmentRequest) {
        log.info("Creating department");
        return ResponseEntity.ok(departmentService.createDepartment(departmentRequest));
    }

    @Operation(summary = "Get department by id", description = "Fetches a department by its unique id.")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable @Parameter(description = "id of the department") Long id) {
        log.info("Fetching department with id: {}", id);
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @Operation(summary = "Update a department", description = "Updates details of an existing department.")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable @Parameter(description = "id of the department") Long id,
            @RequestBody @Valid DepartmentRequest departmentRequest) {
        log.info("Updating department with id: {}", id);
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentRequest));
    }

    @Operation(summary = "Delete a department", description = "Deletes a department from the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable @Parameter(description = "id of the department") Long id) {
        log.info("Deleting department with id: {}", id);
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all departments", description = "Fetches a paginated list of all departments.")
    @GetMapping
    public ResponseEntity<Page<DepartmentResponse>> getAll(
            @RequestParam(required = false) @Parameter(description = "Search parameter (optional)") String searchParam,
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Page size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        log.info("Fetching departments with searchParam: {}", searchParam);
        return ResponseEntity.ok(departmentService.getAll(searchParam, pageable));
    }
}
