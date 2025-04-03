package example.day03._과제3.model.entity;

import example.day03._과제3.model.dto.CourseDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "day03task3course")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class CourseEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    // Entity to Dto
    public CourseDto toDto() {
        return CourseDto.builder().id(this.id).name(this.name)
                .createDate(getCreateDate()).updateDate(getUpdateDate()).build();
    }

    // 양방향 참조
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "courseEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentEntity> studentEntityList = new ArrayList<>();

}
