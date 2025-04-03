package example.day03._자바참조관계;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Category {
    // 클래스 : 인스턴스(객체)의 설계/타입
    // ↓ 멤버변수 선언
    // 기본타입(int)
    private int 카테고리번호;
    // 참조타입(String)
    private String 카테고리명;
    // ↑ 멤버변수 선언
    
    // Category클래스에서 Board타입으로 멤버변수 선언이 가능

    // ↓ 양방향 1:1 참조
    // private Board board;
    // ↓ 양방향 1:M 참조
    // 해당 어노테이션은 밑에 있는 멤버변수를 ToString에 포함시키지 않겠다.
    @ToString.Exclude
    private List<Board> boardList;
}
