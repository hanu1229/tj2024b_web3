package web.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity
@Table(name = "category")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class CategoryEntity {
    
    // 카테코리 식별번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cno;
    
    // 카테고리 이름
    @Column(nullable = false, length = 100)
    private String cname;

    // 양방향
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude @Builder.Default
    private List<ProductEntity> productEntityList = new ArrayList<>();
    
}
