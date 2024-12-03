package zw.co.afrocodemy.afrocodemyjparelationships.domain.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "House Number Cannot be Blank")
    private String houseNumber;
    @NotBlank(message = "Street Cannot be Blank")
    private String street;
    @NotBlank(message = "Zip Code can't be Blank")
    private String zipCode;
    @OneToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private Employee employee;
}