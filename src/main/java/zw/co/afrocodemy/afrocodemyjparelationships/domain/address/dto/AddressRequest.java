package zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddressRequest {
    @NotBlank(message = "Employee Id Cannot be Blank")
    private Long employeeId;
    @NotBlank(message = "House Number Cannot be Blank")
    private String houseNumber;
    @NotBlank(message = "Street Cannot be Blank")
    private String street;
    @NotBlank(message = "Zip Code can't be Blank")
    private String zipCode;
}
