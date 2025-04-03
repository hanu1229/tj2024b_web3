package example.day03._JPA연관관계;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// ↓ 해당 클래스는 데이터에비스와 영속관계로 사용하겠다는 뜻
@Entity
@Table(name = "day03category")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter  @Setter @ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 카테고리 번호
    private int cno;
    // 카테고리 이름
    private String cname;
    
    // 양방향 | 게시물 여러개 참조
    // 양방향일 경우 사용하는 어노테이션
    // @OneToMany(mappedBy = 단방향의멤버변수명) --> 양방향 참조테이블은 자바에서만 참조하게 만들어 줌
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // 빌더 패턴 사용 시 초기 값 대입
    @Builder.Default
    // 순환참조 방지
    @ToString.Exclude
    private List<Board> boardList = new ArrayList<>(); 

}

/*
    ● 영속성 제약조건 옵션 (cascade, fetch)
        ◎ @OneToMany(cascade = CasCadeType.XXX)
            ○ cascade = CascadeType.ALL : 부모[PK]가 삭제/수정/저장되면 자식[FK]도 값이 삭제/수정/저장됨 | REMOVE, MERGE, PERSIST 포함
            ○ cascade = CascadeType.REMOVE : 부모[PK]가 삭제되면 자식[FK]도 같이 삭제됨
            ○ cascade = CascadeType.MERGE : 부모[PK]가 수정되면 자식[FK]도 같이 수정됨
            ○ cascade = CascadeType.PERSIST : 부모[PK]가 저장되면 자식[FK]도 같이 저장됨
            ○ cascade = CascadeType.DETACH : 부모[PK]가 영속을 해지하면 자식[FK]도 같이 해제됨
            ○ cascade = CascadeType.REFRESH : 부모[PK]가 새로 불러올 때 자식[FK]도 같이 새로 불러옴(새로고침)

        ◎ @OneToMany(fetch = FatchType.XXX) | @ManyToOne(fetch = FatchType.XXX)
            ○ fetch = FetchType.EAGER : (기본값 - 즉시로딩) 해당 엔티티를 조회할 때(.findXXX()) 참조되는 모든 객체를 한번에 즉시 불러옴
                특징 : 초기 로딩이 느림 | 불필요한 엔티티를 모두 가져오기 때문에(메모리)로드 기능 저하됨
            ○ fetch = FetchType.LAZY : (지연로딩) 해당 엔티티를 조회할 때(.findXXX()) 참조되는 객체를 불러오지 않고 .getXXX()등 참조할 때 참조되는 객체를 불러옴
                특징 : 초기 로딩이 빠름 | 메모리 사용량을 적절하게 사용함으로 성능을 최적화 할 수 있음


*/
