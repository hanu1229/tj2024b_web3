package example.day02._BaseTime;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 등록
    /// 등록
    public boolean post(BookEntity bookEntity) {
        System.out.println("BookService.post");
        System.out.println("bookEntity = " + bookEntity);
        BookEntity entity = bookRepository.save(bookEntity);
        if(entity.get도서번호() == 0) {
            return false;
        }
        return true;
    }

    // 전체 조회
    public List<BookEntity> get() {
        System.out.println("BookService.get");
        List<BookEntity> result = bookRepository.findAll();
        return result;
    }

    // 수정
    @Transactional
    public boolean put(BookEntity bookEntity) {
        System.out.println("BookService.put");
        System.out.println("bookEntity = " + bookEntity);
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookEntity.get도서번호());
        if(optionalBookEntity.isPresent()) {
            BookEntity entity = optionalBookEntity.get();
            entity.set도서명(bookEntity.get도서명());
            entity.set저자(bookEntity.get저자());
            entity.set출판사(bookEntity.get출판사());
            return true;
        }
        return false;
    }

    // 삭제
    public boolean delete(int 도서번호) {
        System.out.println("BookService.delete");
        System.out.println("도서번호 = " + 도서번호);
        try {
            bookRepository.deleteById(도서번호);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
