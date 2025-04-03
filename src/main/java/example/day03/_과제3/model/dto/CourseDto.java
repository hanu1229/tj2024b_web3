package example.day03._과제3.model.dto;

import example.day03._과제3.model.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class CourseDto {
    private int id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Dto to Entity
    public CourseEntity toEntity() {
        return CourseEntity.builder().id(this.id).name(this.name).build();
    }

}
