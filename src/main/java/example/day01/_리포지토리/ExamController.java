package example.day01._리포지토리;

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
@RequestMapping("/day01/jpa")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // 등록
    @PostMapping()
    public boolean post(@RequestBody() ExamEntity examEntity) {
        boolean result = examService.post(examEntity);
        return result;
    }
    // 전체조회
    @GetMapping()
    public List<ExamEntity> get() {
        return examService.get();
    }
    // 수정
    @PutMapping()
    public boolean put(@RequestBody() ExamEntity examEntity) {
        boolean result = examService.put2(examEntity);
        return result;
    }
    // 삭제
    @DeleteMapping()
    public boolean delete(@RequestParam(name = "id") String id) {
        boolean result = examService.delete(id);
        return result;
    }

}
