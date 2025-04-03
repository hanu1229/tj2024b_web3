package example.day03._JPA연관관계;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "day03board")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 게시물 번호
    private int bno;
    // 게시물 제목
    private String btitle;
    // 게시물 내용
    private String bcontent;

    // 단방향 | 카테고리 참조 | FK필드
    // ↓ FK 필드를 선언하는 방법
    @ManyToOne
    private Category category;
    // 양방향
    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replyList = new ArrayList<>();
    
}
