package todo.model.dto;

import todo.model.entity.TodoEntity;
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
public class TodoDto {
    // 식별번호
    private int id;
    // 할일 제목
    private String title;
    // 할일 내용
    private String content;
    // 할일 상태
    private boolean done;
    // 등록 날짜
    private LocalDateTime createAt;

    // Dto --> Entity Transform
    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .id(this.id).title(this.title)
                .content(this.content).done(this.done).build();
    }

}
