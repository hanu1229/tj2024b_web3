package example.day02._toDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "book1")
public class ExamEntity1 {
    // Entity의 목적 : DB테이블의 영속/연결을 위한 객체
    // 사용되는 계층 : Service Layer (비즈니스 로직)
    @Id
    private String id;
    private String title;
    private int price;

    /// Entity --> dto 객체로 반환하는 함수
    public ExamDto toDto() {
        // 일반적인 방법(생산자 사용)
        // return new ExamDto(this.id, this.title, this.price);
        // 빌더 패턴 | 규칙적인 생성자의 유연성을 보장하는 메소드를 제공하는 패턴
        // 클래스명.builder().필드명(값).필드명(값).필드명(값).build();
        return ExamDto.builder().id(this.id).title(this.title).price(this.price).build();
    }

}
