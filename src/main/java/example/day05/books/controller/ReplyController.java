package example.day05.books.controller;

import example.day05.books.model.dto.ReplyDto;
import example.day05.books.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task05/reply")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReplyController {

    private final ReplyService replyService;

    /** 리뷰 작성 */
    @PostMapping("")
    public ReplyDto replySave(@RequestBody() ReplyDto replyDto) {
        System.out.println("ReplyController.replySave");
        System.out.println("replyDto = " + replyDto);
        return replyService.replySave(replyDto);
    }

    /** 리뷰 전체 조회 */
    @GetMapping("")
    public List<ReplyDto> replyFindAll(@RequestParam(name = "book_id") int bookId) {
        System.out.println("ReplyController.replyFindAll");
        return replyService.replyFindAll(bookId);
    }

    /** 리뷰 삭제 */
    @DeleteMapping("")
    public boolean replyDelete(@RequestBody() ReplyDto replyDto) {
        System.out.println("ReplyController.replyDelete");
        System.out.println("replyDto = " + replyDto);
        return replyService.replyDelete(replyDto);
    }

}
