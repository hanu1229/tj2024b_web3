package web.model.entity;

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
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String email;
    private String password;
    private String name;
    
    // 양방향 : FK 엔티티를 여러개 가지므로 List타입으로 만듬
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // 양방향 설계시 toString 룸복의 순환참조 방지
    @ToString.Exclude
    // 엔티티 객체를 생성 시 빌더 메소드를 사용하면 기본값 사용
    @Builder.Default
    private List<ProductEntity> productEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // Entity --> Dto
    public MemberDto toDto() {
        return MemberDto.builder().no(this.no).email(this.email).password(this.password).name(this.name).build();
    }

}
