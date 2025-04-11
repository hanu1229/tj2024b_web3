package example.day05.books.model.entity;

import example.day05.books.model.dto.ReplyDto;
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
@Table(name = "reply")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ReplyEntity extends BaseTime {
    
    // 리뷰 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // 내용
    private String comment;
    // 비밀번호
    private String password;
    // 추천 번호 ( FK )
    @ManyToOne
    private BookEntity bookEntity;

    // Entity --> Dto Transform
    public ReplyDto toDto() {
        return ReplyDto.builder()
                .id(this.id).comment(this.comment).password(this.password).bookId(bookEntity.getId())
                .createDate(getCreateDate()).updateDate(getUpdateDate())
                .build();
    }
    
}
