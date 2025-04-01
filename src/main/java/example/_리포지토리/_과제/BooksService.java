package example._리포지토리._과제;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;

    /** 도서 등록 */
    public boolean post(BooksEntity booksEntity) {
        System.out.println(">> BooksService.post");
        System.out.println(">> booksEntity = " + booksEntity);
        BooksEntity result = booksRepository.save(booksEntity);
        return true;
    }

    /** 도서 전체 조회 */
    public List<BooksEntity> findAll() {
        System.out.println(">> BooksService.findAll");
        List<BooksEntity> result = booksRepository.findAll();
        return result;
    }

    /** 특정 도서 정보 수정 */
    @Transactional
    public boolean put(BooksEntity booksEntity) {
        System.out.println(">> BooksService.put");
        System.out.println(">> booksEntity = " + booksEntity);
        Optional<BooksEntity> optionalBooksEntity = booksRepository.findById(booksEntity.getId());
        if(optionalBooksEntity.isPresent()) {
            BooksEntity entity = optionalBooksEntity.get();
            entity.setTitle(booksEntity.getTitle());
            entity.setWriter(booksEntity.getWriter());
            entity.setPublisher(booksEntity.getPublisher());
            entity.setYear(booksEntity.getYear());
            return true;
        }
        return false;
    }

    /** 특정 도서 삭제 */
    public boolean delete(int id) {
        System.out.println(">> BooksService.delete");
        System.out.println(">> id = " + id);
        booksRepository.deleteById(id);
        return true;
    }

}
