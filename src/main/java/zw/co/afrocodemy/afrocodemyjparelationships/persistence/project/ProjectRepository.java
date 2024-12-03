package zw.co.afrocodemy.afrocodemyjparelationships.persistence.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.project.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Page<Project> findByNameContainingIgnoreCase(String searchParam, Pageable pageable);
}
