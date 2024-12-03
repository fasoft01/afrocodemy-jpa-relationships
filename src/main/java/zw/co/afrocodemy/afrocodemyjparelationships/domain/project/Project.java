package zw.co.afrocodemy.afrocodemyjparelationships.domain.project;

import jakarta.persistence.*;
import lombok.*;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long period;
    @ManyToMany(mappedBy = "employee")
    private List<Employee> employees;
}
