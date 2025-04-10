package product.service;

import product.model.dto.ProductDto;
import product.model.entity.ProductEntity;
import product.model.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    /** 비품 등록 */
    public boolean productSave(ProductDto productDto) {
        System.out.println("ProductService.productSave");
        System.out.println("productDto = " + productDto);
        /*
        // 방법1 - JPA 기본 제공 메소드 사용
        ProductEntity saveEntity = productRepository.save(productDto.toEntity());
        if(saveEntity.getId() > 0) {
            return saveEntity.toDto();
        } else {
            return null;
        }
        */
        // 방법2 - 네이티브쿼리를 이용한 방법
        LocalDateTime now = LocalDateTime.now();
        String createDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        String updateDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        ProductEntity entity = productDto.toEntity();
        int result = productRepository.saveNative(
                entity.getName(), entity.getDescription(), entity.getQuantity(),
                createDate, updateDate
        );
        if(result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /** 비품 전체 조회 */
    public List<ProductDto> productFindAll() {
        System.out.println("ProductService.productFindAll");
        // List<ProductEntity> productEntityList = productRepository.findAll();
        // 방법1 - JPA 기본 제공 메소드 사용
        /*
        return productRepository.findAll().stream()
                .map(ProductEntity::toDto).collect(Collectors.toList());
         */
        // 방법2 - 네이티브쿼리를 이용한 방법
        return productRepository.findAllNative().stream().map(ProductEntity::toDto).collect(Collectors.toList());

    }

    /** 비품 개별 조회 */
    public ProductDto productFindById(int id) {
        System.out.println("ProductController.productFindById");
        System.out.println("id = " + id);
        // 방법1 - JPA 기본 제공 메소드 사용
        /*
        return productRepository.findById(id).map(ProductEntity::toDto).orElse(null);
         */
        // 방법2 - 네이티브쿼리를 이용한 방법
        return productRepository.findByIdNative(id).map(ProductEntity::toDto).orElse(null);
    }

    /** 비품 수정 기능 */
    public ProductDto productUpdate(ProductDto productDto) {
        System.out.println("ProductController.productUpdate");
        System.out.println("productDto = " + productDto);
        // 방법1 - JPA 기본 제공 메소드 사용
        /*
        return productRepository.findById(productDto.getId())
                .map((entity) -> {
                    entity.setName(productDto.getName());
                    entity.setDescription(productDto.getDescription());
                    entity.setQuantity(productDto.getQuantity());
                    return entity.toDto();
                }).orElse(null);
         */
        // 방법2 - 네이티브쿼리를 이용한 방법
        return productRepository.findByIdNative(productDto.getId())
                .map((entity) -> {
                    entity.setName(productDto.getName());
                    entity.setDescription(productDto.getDescription());
                    entity.setQuantity(productDto.getQuantity());
                    return entity.toDto();
                }).orElse(null);
    }

    /** 비품 삭제 기능 */
    public boolean productDelete(int id) {
        System.out.println("ProductController.productDelete");
        System.out.println("id = " + id);
        // 방법1 - JPA 기본 제공 메소드 사용
        /*
        boolean result = productRepository.existsById(id);
        if(result) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
        */
        // 방법2 - 네이티브쿼리를 이용한 방법
        Optional<ProductEntity> optional = productRepository.findByIdNative(id);
        if(optional.isPresent()) {
            productRepository.deleteByIdNative(id);
            return true;
        }
        return false;
    }

    /** 비품 전체 조회 + 페이징 처리 */
    public List<ProductDto> productFindByPage(int page, int size) {
        System.out.println("ProductService.productFindByPage");
        System.out.println("page = " + page + ", size = " + size);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageRequest);
        System.out.println("productEntityPage = " + productEntityPage);
        System.out.println("전체 비품 수 = " + productEntityPage.getTotalElements());
        System.out.println("전체 페이지 수  = " + productEntityPage.getTotalPages());
        System.out.println("페이지 반환 값 = " + productEntityPage.getContent());
        List<ProductDto> productDtoList = new ArrayList<>();
        for(ProductEntity entity : productEntityPage) {
            ProductDto productDto = entity.toDto();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

}
