package zw.co.afrocodemy.afrocodemyjparelationships.domain.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.Address;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.Department;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.Project;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.utils.Role;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String identifier;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Address address;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @ManyToMany
    @JoinTable(
            name = "project_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;
}