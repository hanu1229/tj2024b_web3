package book.controller;

import book.model.dto.BookDto;
import book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/task05/book")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;

    /** 책 추천 등록 */
    @PostMapping("")
    public BookDto bookSave(@RequestBody() BookDto bookDto) {
        System.out.println("BookController.bookSave");
        System.out.println("bookDto = " + bookDto);
        return bookService.bookSave(bookDto);
    }

    /** 책 추천 목록 조회 */
    @GetMapping("")
    public List<BookDto> bookFindAll() {
        System.out.println("BookController.bookFindAll");
        return bookService.bookFindAll();
    }

    /** 책 추천 상세 조회 */
    @GetMapping("/detail")
    public BookDto bookFindById(@RequestParam(name = "id") int id) {
        System.out.println("BookController.bookFindById");
        System.out.println("id = " + id);
        return bookService.bookFindById(id);
    }

    /** 책 추천 수정 */
    @PutMapping("")
    public boolean bookUpdate(@RequestBody() BookDto bookDto) {
        System.out.println("BookController.bookUpdate");
        System.out.println("bookDto = " + bookDto);
        return bookService.bookUpdate(bookDto);
    }

    /** 책 추천 삭제 */
    @DeleteMapping("")
    public boolean bookDelete(@RequestBody() BookDto bookDto) {
        System.out.println("BookController.bookDelete");
        System.out.println("bookDto = " + bookDto);
        return bookService.bookDelete(bookDto);
    }

}
