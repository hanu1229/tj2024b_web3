package example.day05.books.service;

import example.day05.books.model.dto.ReplyDto;
import example.day05.books.model.entity.BookEntity;
import example.day05.books.model.entity.ReplyEntity;
import example.day05.books.model.repository.BookRepository;
import example.day05.books.model.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BookRepository bookRepository;

    /** 리뷰 작성 */
    public ReplyDto replySave(ReplyDto replyDto) {
        System.out.println("ReplyService.replySave");
        System.out.println("replyDto = " + replyDto);
        ReplyEntity replyEntity = replyDto.toEntity();
        Optional<BookEntity> optional = bookRepository.findById(replyDto.getBookId());
        if(optional.isPresent()) {
            BookEntity bookEntity = optional.get();
            replyEntity.setBookEntity(bookEntity);
            replyRepository.save(replyEntity);
            return replyEntity.toDto();
        }
        return null;
    }

    /** 리뷰 전체 조회 */
    public List<ReplyDto> replyFindAll(int bookId) {
        System.out.println("ReplyService.replyFindAll");
        System.out.println("bookId = " + bookId);
        BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
        if(bookEntity != null) {
            List<ReplyEntity> replyEntityList = bookEntity.getReplyEntityList();
            List<ReplyDto> replyDtoList = new ArrayList<>();
            for(ReplyEntity replyEntity : replyEntityList) {
                ReplyDto reply = replyEntity.toDto();
                replyDtoList.add(reply);
            }
            return replyDtoList;
        }
        return null;
    }

    /** 리뷰 삭제 */
    public boolean replyDelete(int id) {
        System.out.println("ReplyService.replyDelete");
        System.out.println("id = " + id);
        ReplyEntity replyEntity = replyRepository.findById(id).orElse(null);
        if(replyEntity != null) {
            replyRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
