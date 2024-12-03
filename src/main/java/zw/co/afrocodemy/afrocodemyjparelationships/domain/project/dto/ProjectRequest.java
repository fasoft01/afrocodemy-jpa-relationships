package zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProjectRequest {
    private String name;
    private Long period;
}
