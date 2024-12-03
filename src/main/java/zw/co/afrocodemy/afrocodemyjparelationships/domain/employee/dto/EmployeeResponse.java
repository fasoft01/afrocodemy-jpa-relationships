package zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.utils.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private String identifier;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private LocalDate birthDate;
    private Role role;
}
