package product.model.entity;

import product.model.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "task04")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ProductEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 비품번호
    private int id;
    // 비품명
    private String name;
    // 비품 설명
    private String description;
    // 비품 수량
    private int quantity;

    // Entity --> Dto Transform
    public ProductDto toDto() {
        return ProductDto.builder()
                .id(this.id).name(this.name).description(this.description).quantity(this.quantity)
                .createdDate(this.getCreateDate()).modifiedDate(this.getUpdateDate())
                .build();
    }

}
