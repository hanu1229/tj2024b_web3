package example.day01._과제2;

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
@RequestMapping("/day01/task2")
@RequiredArgsConstructor
public class BooksController {

    private final BooksService booksService;

    /** 도서 등록 */
    @PostMapping()
    public boolean post(@RequestBody() BooksEntity booksEntity) {
        System.out.println(">> BooksController.post");
        System.out.println(">> booksEntity = " + booksEntity);
        boolean result = booksService.post(booksEntity);
        return result;
    }

    /** 도서 전체 조회 */
    @GetMapping()
    public List<BooksEntity> findAll() {
        System.out.println(">> BooksController.findAll");
        List<BooksEntity> result = booksService.findAll();
        return result;
    }

    /** 특정 도서 정보 수정 */
    @PutMapping()
    public boolean put(@RequestBody() BooksEntity booksEntity) {
        System.out.println(">> BooksController.put");
        System.out.println(">> booksEntity = " + booksEntity);
        boolean result = booksService.put(booksEntity);
        return result;
    }

    /** 특정 도서 삭제 */
    @DeleteMapping()
    public boolean delete(@RequestParam(name = "id") int id) {
        System.out.println(">> BooksController.delete");
        System.out.println(">> id = " + id);
        boolean result = booksService.delete(id);
        return result;
    }

}
