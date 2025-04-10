package todo.model.entity;

import todo.model.dto.TodoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "todo")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class TodoEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 식별번호
    private int id;
    // 할일 제목
    private String title;
    // 할일 내용
    private String content;
    // 할일 상태
    private boolean done;

    // Entity --> Dto Transform
    public TodoDto toDto() {
        return TodoDto.builder()
                .id(this.id).title(this.title)
                .content(content).done(this.done)
                .createAt(this.getCreateDate()).build();
    }

}
