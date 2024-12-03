package zw.co.afrocodemy.afrocodemyjparelationships.service.employee;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.utils.Role;

public interface EmployeeService {
    EmployeeResponse createEmployee(@Valid EmployeeRequest employeeRequest);

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(Long id, @Valid EmployeeRequest employeeRequest);

    void deleteEmployee(Long id);

    Page<EmployeeResponse> getAll(Role role, String searchParam, Pageable pageable);

    Employee findById(Long id);
}
