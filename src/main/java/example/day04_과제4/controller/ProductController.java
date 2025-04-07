package example.day04_과제4.controller;

import example.day04_과제4.model.dto.ProductDto;
import example.day04_과제4.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("day04/task04")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /** 비품 등록 */
    @PostMapping("")
    public boolean productSave(@RequestBody() ProductDto productDto) {
        System.out.println("ProductController.productSave");
        System.out.println("productDto = " + productDto);
        return productService.productSave(productDto);
    }

    /** 비품 전체 조회 */
    @GetMapping("")
    public List<ProductDto> productFindAll() {
        System.out.println("ProductController.productFindAll");
        return productService.productFindAll();
    }

    /** 비품 개별 조회 */
    @GetMapping("/view")
    public ProductDto productFindById(@RequestParam(name = "id") int id) {
        System.out.println("ProductController.productFindById");
        System.out.println("id = " + id);
        return productService.productFindById(id);
    }

    /** 비품 수정 기능 */
    @PutMapping("")
    public ProductDto productUpdate(@RequestBody() ProductDto productDto) {
        System.out.println("ProductController.productUpdate");
        System.out.println("productDto = " + productDto);
        return productService.productUpdate(productDto);
    }


    /** 비품 삭제 기능 */
    @DeleteMapping("")
    public boolean productDelete(@RequestParam(name = "id") int id) {
        System.out.println("ProductController.productDelete");
        System.out.println("id = " + id);
        return productService.productDelete(id);
    }

    /** 비품 전체 조회 + 페이징 처리 */
    @GetMapping("page")
    public List<ProductDto> productFindByPage(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "3") int size
    ) {
        return productService.productFindByPage(page, size);
    }

}
