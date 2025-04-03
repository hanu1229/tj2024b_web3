package example.day03._JPA연관관계;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "day03reply")
@Builder
@Getter @Setter @ToString
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 댓글 번호
    private int rno;
    // 댓글 내용
    private String rcontent;

    // 단방향 | 게시물 참조 | FK필드
    @ManyToOne
    private Board board;

}
