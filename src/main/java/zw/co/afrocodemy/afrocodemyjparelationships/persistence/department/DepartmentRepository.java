package zw.co.afrocodemy.afrocodemyjparelationships.persistence.department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.department.Department;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Page<Department> findAllByNameContainingIgnoreCase(String searchParam, Pageable pageable);
}
