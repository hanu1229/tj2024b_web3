package example.day03._과제3.model.entity;

import example.day03._과제3.model.dto.StudentDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "day03task3student")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class StudentEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;


    // Entity To Dto
    public StudentDto toDto() {
        return StudentDto.builder().id(this.id).name(this.name)
                .createDate(getCreateDate()).updateDate(getUpdateDate()).build();
    }

    // 단방향 참조
    @ManyToOne
    private CourseEntity courseEntity;

}
