package zw.co.afrocodemy.afrocodemyjparelationships.service.employee.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.utils.Role;
import zw.co.afrocodemy.afrocodemyjparelationships.persistence.employee.EmployeeRepository;
import zw.co.afrocodemy.afrocodemyjparelationships.service.employee.EmployeeService;
import zw.co.afrocodemy.afrocodemyjparelationships.service.exceptions.RecordNotFoundException;
import zw.co.afrocodemy.afrocodemyjparelationships.service.responsemappers.ResponseMapper;

import java.util.Objects;

@Data
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        log.info("Creating employee with identifier: {}", employeeRequest.getIdentifier());

        var employee = Employee.builder()
                .identifier(employeeRequest.getIdentifier())
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .birthDate(employeeRequest.getBirthDate())
                .role(employeeRequest.getRole())
                .build();

        employeeRepository.save(employee);

        log.info("Employee created with ID: {}", employee.getId());
        return ResponseMapper.mapToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);

        var employee = findById(id);

        log.info("Employee found with ID: {}", employee.getId());
        return ResponseMapper.mapToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        log.info("Updating employee with ID: {}", id);

        var employee = findById(id);

        employee.setIdentifier(employeeRequest.getIdentifier());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setBirthDate(employeeRequest.getBirthDate());
        employee.setRole(employeeRequest.getRole());

        employeeRepository.save(employee);

        log.info("Employee updated with ID: {}", employee.getId());
        return ResponseMapper.mapToEmployeeResponse(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);

        var employee = findById(id);
        employeeRepository.delete(employee);

        log.info("Employee with ID: {} deleted successfully", id);
    }

    @Override
    public Page<EmployeeResponse> getAll(Role role,String searchParam, Pageable pageable) {
        log.info("Fetching employees with search parameter: {}", searchParam);

        Page<EmployeeResponse> employees;
        if (Objects.nonNull(searchParam)) {
            employees = employeeRepository.findAllByIdentifierContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                            searchParam, searchParam, searchParam, searchParam, pageable)
                    .map(ResponseMapper::mapToEmployeeResponse);
        } else if (Objects.nonNull(role)) {
            employees = employeeRepository.findAllByRole(role,pageable).map(ResponseMapper::mapToEmployeeResponse);
        } else {
            employees = employeeRepository.findAll(pageable)
                    .map(ResponseMapper::mapToEmployeeResponse);
        }

        log.info("Found {} employees", employees.getTotalElements());
        return employees;
    }

    @Override
    public Employee findById(Long id) {
        log.info("Looking for employee with ID: {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee with ID {} not found", id);
                    return new RecordNotFoundException(
                            String.format("Employee with ID %d not found", id)
                    );
                });
    }
}
