package example.day02._BaseTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// DB테이블의 레코드 생성날짜와 수정날짜를 감지하는 엔티티
// 해당 클래스는 일반 엔티티가 아닌 상속 엔티티로 사용하겠다는 뜻(공통 부분을 만들 때 사용)
// AppStart클래스에서 @EnableJpaAuditing
@MappedSuperclass
// 해당 클래스의 멤버변수들은 JPA의 감시 대상이 됨
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTime {
    
    // 엔티티/레코드의 영속/생성 날짜/시간을 자동 주입
    // 예시 : 회원가입날짜, 제품등록일, 주문일
    @CreatedDate
    private LocalDateTime 생성날짜시간;

    // 엔티티/레코드의 수정 날짜/시간을 자동 주입
    // 예시 : 회원수정날짜, 제품수정일, 주문수정일
    @LastModifiedDate
    private LocalDateTime 수정날짜시간;
    
}
