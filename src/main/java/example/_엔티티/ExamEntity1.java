package example._엔티티;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// 해당 클래스를 엔티티로 사용
@Entity
public class ExamEntity1 {
    // 멤버 변수
    // 식별키(primary key) 필수
    @Id
    private int cal1;
    private String col2;
    private double col3;
}
