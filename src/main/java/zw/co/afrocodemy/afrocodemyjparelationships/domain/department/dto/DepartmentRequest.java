package zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DepartmentRequest {
    @NotBlank(message = "Name cannot be Blank")
    private String name;
}
