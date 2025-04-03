package example.day03._자바참조관계;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Reply {
    private int 댓글번호;
    private String 댓글내용;
    
    // ↓ 단방향 참조
    private Board board;
}
