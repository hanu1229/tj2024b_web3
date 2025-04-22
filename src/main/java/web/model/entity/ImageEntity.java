package web.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "img")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ImageEntity {

    // 이미지 식별번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ino;
    // 이미지명
    @Column(nullable = false)
    private String iname;

    // 단방향
    @ManyToOne
    @JoinColumn(name = "pno")
    private ProductEntity productEntity;

}
