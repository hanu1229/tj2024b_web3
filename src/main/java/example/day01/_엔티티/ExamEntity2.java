package example.day01._엔티티;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

// ↓ 해당 클래스를 DB테이블과 매핑 관계 주입(ORM) | 영속성 컨텍스트에 저장
@Entity
// ↓ DB 테이블명 정의 | 생략시 클래스명으로 정의
@Table(name = "exam2")
public class ExamEntity2 {
    // ↓ primery key 제약조건을 정의
    @Id
    // ↓ 생성된 값 | strategy = GenerationType.IDENTITY --> auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // ↓
    @Column(nullable = true, unique = false)
    private String col1;

    @Column(nullable = false, unique = true)
    private String col2;

    @Column(columnDefinition = "longtext")
    private String col3;

    @Column(name = "제품명", length = 30, insertable = false, updatable = true)
    private String col4;

    @Column
    private LocalDate col5;
    @Column
    private LocalDateTime col6;
}
