package zw.co.afrocodemy.afrocodemyjparelationships.service.department.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.Department;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto.DepartmentRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto.DepartmentResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.persistence.department.DepartmentRepository;
import zw.co.afrocodemy.afrocodemyjparelationships.service.department.DepartmentService;
import zw.co.afrocodemy.afrocodemyjparelationships.service.responsemappers.ResponseMapper;

@Data
@RequiredArgsConstructor
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Page<DepartmentResponse> getAll(String searchParam, Pageable pageable) {
        log.info("Fetching all departments with search param: {}", searchParam);

        Page<Department> departments;
        if (searchParam != null && !searchParam.isEmpty()) {
            log.debug("Searching departments by name containing: {}", searchParam);
            departments = departmentRepository.findAllByNameContainingIgnoreCase(searchParam, pageable);
        } else {
            log.debug("Fetching all departments without filters");
            departments = departmentRepository.findAll(pageable);
        }

        log.info("Fetched {} departments", departments.getTotalElements());
        return departments.map(ResponseMapper::mapToDepartmentResponse);
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        log.info("Creating new department with name: {}", departmentRequest.getName());

        var department = Department.builder()
                .name(departmentRequest.getName())
                .build();

        log.debug("Department entity built: {}", department);
        var savedDepartment = departmentRepository.save(department);
        log.info("Department created successfully with id: {}", savedDepartment.getId());

        return ResponseMapper.mapToDepartmentResponse(savedDepartment);
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        log.info("Fetching department with id: {}", id);
        var department = findById(id);
        log.debug("Found department: {}", department);
        return ResponseMapper.mapToDepartmentResponse(department);
    }

    @Override
    public Department findById(Long id) {
        log.info("Finding department by id: {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Department not found with id: {}", id);
                    return new RuntimeException("Department not found with id: " + id);
                });
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
        log.info("Updating department with id: {}", id);

        Department existingDepartment = findById(id);
        log.debug("Existing department: {}", existingDepartment);

        existingDepartment.setName(departmentRequest.getName());
        log.debug("Updated department details: {}", existingDepartment);

        Department updatedDepartment = departmentRepository.save(existingDepartment);
        log.info("Department updated successfully with id: {}", updatedDepartment.getId());

        return ResponseMapper.mapToDepartmentResponse(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        log.info("Deleting department with id: {}", id);

        var department = findById(id);
        log.debug("Department to be deleted: {}", department);

        departmentRepository.delete(department);
        log.info("Department deleted successfully with id: {}", id);
    }
}
