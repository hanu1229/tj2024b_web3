package example.day02._toDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter @ToString
public class ExamDto {
    // DTO 목적 : 서로 다른 계층/레이어간의 이동 객체
    // VO 목적 : 수정불가능한 객체(setter없음/불변)
    // 사용되는 계층 : 컨트롤러 레이어 (View <-- Dto --> Controller <-- Dto --> Service)

    private String id;
    private String title;
    private int price;

    /// Dto --> Entity 객체로 반환하는 함수
    public ExamEntity1 toEntity() {
        return ExamEntity1.builder().id(this.id).title(this.title).price(this.price).build();
    }

}
