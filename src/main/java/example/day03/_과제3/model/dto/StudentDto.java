package example.day03._과제3.model.dto;

import example.day03._과제3.model.entity.StudentEntity;
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
public class StudentDto {
    private int id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    // 과정 번호를 받는 변수
    private int courseEntityId;

    // Dto to Entity
    public StudentEntity toEntity() {
        return StudentEntity.builder().id(this.id).name(this.name).build();
    }

}
