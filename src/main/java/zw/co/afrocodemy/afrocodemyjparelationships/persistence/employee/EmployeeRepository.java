package zw.co.afrocodemy.afrocodemyjparelationships.persistence.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.utils.Role;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAllByIdentifierContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String searchParam, String searchParam1, String searchParam2, String searchParam3, Pageable pageable);

    Page<Employee> findAllByRole(Role role, Pageable pageable);
}
