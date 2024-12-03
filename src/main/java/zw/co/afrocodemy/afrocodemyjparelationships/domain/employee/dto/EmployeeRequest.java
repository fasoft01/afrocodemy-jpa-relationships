package zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.utils.Role;

import java.time.LocalDate;
@Data
@RequiredArgsConstructor
public class EmployeeRequest {
    private String identifier;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private LocalDate birthDate;
    private Role role;
}
