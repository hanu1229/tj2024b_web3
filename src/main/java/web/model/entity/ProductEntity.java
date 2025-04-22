package web.model.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ProductEntity {

    // 제품 식별번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pno;
    // 제품명
    @Column(nullable = false)
    private String pname;
    // 제품설명
    @Column(nullable = false, columnDefinition = "longtext")
    private String pcontent;
    // 제품가격
    @Column(nullable = false)
    @ColumnDefault("0")
    private int pprice;
    // 조회수
    @Column(nullable = false)
    @ColumnDefault("0")
    private int pview;

    // 단방향 : 참조형 PK필드가 존재하는 엔티티 필드 생성
    @ManyToOne
    @JoinColumn(name = "mno")
    private MemberEntity memberEntity;
    @ManyToOne
    @JoinColumn(name = "cno")
    private CategoryEntity categoryEntity;

    // 양방향
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default @ToString.Exclude
    private List<ImageEntity> imageEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default @ToString.Exclude
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

}
