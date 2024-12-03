package zw.co.afrocodemy.afrocodemyjparelationships.domain.department;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be Blank")
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

}