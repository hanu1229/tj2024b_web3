package example.day03._자바참조관계;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.LongStream;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Board {
    private int 게시물번호;
    private String 게시물제목;
    private String 게시물내용;

    // Board클래스에서 Category타입으로 멤버변수 선언이 가능
    // ↓ 단방향 참조
    private Category category;
    // ↓ 양방향 1:1 참조
    private Reply reply;
    // ↓ 양방향 1:M 참조
    // 해당 어노테이션은 밑에 있는 멤버변수를 ToString에 포함시키지 않겠다.
    @ToString.Exclude
    private List<Reply> replyList;

}
