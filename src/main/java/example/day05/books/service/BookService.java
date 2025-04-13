package example.day05.books.service;

import example.day05.books.model.dto.BookDto;
import example.day05.books.model.entity.BookEntity;
import example.day05.books.model.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.bcel.BcelTypeMunger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /** 책 추천 등록 */
    public BookDto bookSave(BookDto bookDto) {
        System.out.println("BookService.bookSave");
        System.out.println("bookDto = " + bookDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(bookDto.getPassword());
        System.out.println("hashedPassword = " + hashedPassword);
        bookDto.setPassword(hashedPassword);
        // 암호 검증 방법
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // boolean result = passwordEncoder.matches(비교할 자료, 암호화된 자료);
        // └─ 일치하면 true, 불일치이면 false
        BookEntity bookEntity = bookRepository.save(bookDto.toEntity());
        return bookEntity.toDto();
    }

    /** 책 추천 목록 조회 */
    public List<BookDto> bookFindAll() {
        System.out.println("BookService.bookFindAll");
        List<BookEntity> bookEntityList = bookRepository.findAll();
        return bookEntityList.stream().map(BookEntity::toDto).collect(Collectors.toList());
    }

    /** 책 추천 상세 조회 */
    public BookDto bookFindById(int id) {
        System.out.println("BookService.bookFindById");
        System.out.println("id = " + id);
        Optional<BookEntity> optional = bookRepository.findById(id);
        if(optional.isPresent()) {
            BookDto bookDto = optional.get().toDto();
            System.out.println(bookDto);
            return bookDto;
        }
        return null;
    }

    /** 책 추천 수정 */
    public boolean bookUpdate(BookDto bookDto) {
        System.out.println("BookService.bookUpdate");
        System.out.println("bookDto = " + bookDto);
        Optional<BookEntity> optional = bookRepository.findById(bookDto.getId());
        if(optional.isPresent()) {
            BookEntity bookEntity = optional.get();
            // 암호 검증 방법
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean result = passwordEncoder.matches(bookDto.getPassword(), bookEntity.getPassword());
            // 일치하면 true, 불일치이면 false
            if(result) {
                bookEntity.setTitle(bookDto.getTitle());
                bookEntity.setWriter(bookDto.getWriter());
                bookEntity.setIntro(bookDto.getIntro());
                return true;
            } else {
                System.out.println("추천 수정 >>  비밀번호 틀림");
                return false;
            }
        }
        return false;
    }

    /** 책 추천 삭제 */
    public boolean bookDelete(BookDto bookDto) {
        System.out.println("BookService.bookDelete");
        System.out.println("bookDto = " + bookDto);
        BookEntity bookEntity = bookRepository.findById(bookDto.getId()).orElse(null);
        if(bookEntity != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean result = passwordEncoder.matches(bookDto.getPassword(), bookEntity.getPassword());
            if(result) {
                bookRepository.deleteById(bookEntity.getId());
                return true;
            }
        }
        System.out.println("추천 삭제 >> 비밀번호 틀림");
        return false;
    }

}
