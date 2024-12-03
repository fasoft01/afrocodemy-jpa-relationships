package zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    @NotBlank(message = "Name cannot be Blank")
    private String name;
}
