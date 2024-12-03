package zw.co.afrocodemy.afrocodemyjparelationships.service.responsemappers;

import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.Address;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto.AddressResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.Employee;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.employee.dto.EmployeeResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.Department;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.dto.DepartmentResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.Project;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto.ProjectResponse;

public class ResponseMapper {

    /**
     * Maps an Employee entity to an EmployeeResponse DTO.
     *
     * @param employee The Employee entity to map.
     * @return The mapped EmployeeResponse DTO.
     */
    public static EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .identifier(employee.getIdentifier())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .birthDate(employee.getBirthDate())
                .role(employee.getRole())
                .build();
    }

    /**
     * Maps an Address entity to an AddressResponse DTO.
     *
     * @param address The Address entity to map.
     * @return The mapped AddressResponse DTO.
     */
    public static AddressResponse mapToAddressResponse(Address address) {
        return new AddressResponse(
                address.getEmployee().getId(),
                address.getHouseNumber(),
                address.getStreet(),
                address.getZipCode()
        );
    }

    /**
     * Maps a Department entity to a DepartmentResponse DTO.
     *
     * @param department The Department entity to map.
     * @return The mapped DepartmentResponse DTO.
     */
    public static DepartmentResponse mapToDepartmentResponse(Department department) {
        return new DepartmentResponse(department.getName());
    }

    public static ProjectResponse mapToProjectResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .employeeId(project.getEmployeeId())
                .build();
    }
}
