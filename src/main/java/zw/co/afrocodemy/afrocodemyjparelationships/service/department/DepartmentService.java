package zw.co.afrocodemy.afrocodemyjparelationships.service.department;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.Department;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto.DepartmentRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto.DepartmentResponse;

public interface DepartmentService {
    Page<DepartmentResponse> getAll(String searchParam, Pageable pageable);

    DepartmentResponse getDepartmentById(Long id);

    Department findById(Long id);

    void deleteDepartment(Long id);

    DepartmentResponse updateDepartment(Long id, @Valid DepartmentRequest departmentRequest);

    DepartmentResponse createDepartment(@Valid DepartmentRequest departmentRequest);
}
