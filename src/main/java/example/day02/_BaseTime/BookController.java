package example.day02._BaseTime;

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
@RequestMapping("/day02/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 등록
    @PostMapping("")
    public boolean post(@RequestBody() BookEntity bookEntity) {
        System.out.println("BookController.post");
        System.out.println("bookEntity = " + bookEntity);
        boolean result = bookService.post(bookEntity);
        return result;
    }

    // 전체 조회
    @GetMapping("")
    public List<BookEntity> get() {
        System.out.println("BookController.get");
        List<BookEntity> result = bookService.get();
        return result;
    }

    // 수정
    @PutMapping("")
    public boolean put(@RequestBody() BookEntity bookEntity) {
        System.out.println("BookController.put");
        System.out.println("bookEntity = " + bookEntity);
        boolean result = bookService.put(bookEntity);
        return result;
    }

    // 삭제
    @DeleteMapping("")
    public boolean delete(@RequestParam(name = "도서번호") int 도서번호) {
        System.out.println("BookController.delete");
        System.out.println("도서번호 = " + 도서번호);
        boolean result = bookService.delete(도서번호);
        return result;
    }

}
