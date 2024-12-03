package zw.co.afrocodemy.afrocodemyjparelationships.domain.project.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private Long period;
}
