package zw.co.afrocodemy.afrocodemyjparelationships.api.employee;

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
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.utils.Role;
import zw.co.afrocodemy.afrocodemyjparelationships.service.employee.EmployeeService;

@Slf4j
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@SecurityRequirement(name = "authorization")
@Tag(name = "Employee Controller", description = "APIs for managing employees, including CRUD operations and pagination")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create a new employee", description = "Adds a new employee to the database.")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @RequestBody @Valid EmployeeRequest employeeRequest) {
        log.info("Creating employee with identifier: {}", employeeRequest.getIdentifier());
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @Operation(summary = "Get employee by ID", description = "Fetches an employee by their unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable @Parameter(description = "Unique ID of the employee") Long id) {
        log.info("Fetching employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Operation(summary = "Update an employee", description = "Updates details of an existing employee.")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable @Parameter(description = "Unique ID of the employee") Long id,
            @RequestBody @Valid EmployeeRequest employeeRequest) {
        log.info("Updating employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
    }

    @Operation(summary = "Delete an employee", description = "Deletes an employee from the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable @Parameter(description = "Unique ID of the employee") Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all employees", description = "Fetches a paginated list of all employees.")
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAll(
            @RequestParam(required = false) @Parameter(description = "Search parameter (optional)") String searchParam,
            @RequestParam(required = false) @Parameter(description = "Employee role (optional)") Role role,
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Page size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        log.info("Fetching employees with searchParam: {}", searchParam);
        return ResponseEntity.ok(employeeService.getAll(role , searchParam, pageable));
    }
}
