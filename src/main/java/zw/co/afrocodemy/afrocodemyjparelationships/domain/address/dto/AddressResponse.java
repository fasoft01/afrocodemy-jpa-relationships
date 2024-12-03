package zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long employeeId;
    private String houseNumber;
    private String street;
    private String zipCode;
}
