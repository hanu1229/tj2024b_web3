package product.model.dto;

import product.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ProductDto {

    // 비품번호
    private int id;
    // 비품명
    private String name;
    // 비품 설명
    private String description;
    // 비품 수량
    private int quantity;
    // 등록 시간
    private LocalDateTime createdDate;
    // 수정 시간
    private LocalDateTime modifiedDate;

    // Dto --> Entity Transform
    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .id(this.id).name(this.name)
                .description(this.description).quantity(this.quantity)
                .build();
    }

}
