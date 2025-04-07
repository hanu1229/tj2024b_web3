package example.day04.controller;

import example.day04.model.dto.TodoDto;
import example.day04.service.TodoService;
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
@RequestMapping("/day04/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /** 개별 등록 */
    @PostMapping("")
    public TodoDto todoSave(@RequestBody() TodoDto todoDto) {
        return todoService.todoSave(todoDto);
    }

    /** 전체 조회 */
    @GetMapping("")
    public List<TodoDto> todoFindAll() {
        return todoService.todoFindAll();
    }

    /** 개별 조회 */
    @GetMapping("/view")
    public TodoDto todoFindById(@RequestParam(name = "id") int id) {
        return todoService.todoFindById(id);
    }

    /** 개별 수정 */
    @PutMapping("")
    public TodoDto todoUpdate(@RequestBody() TodoDto todoDto) {
        return todoService.todoUpdate(todoDto);
    }

    /** 개별 삭제 */
    @DeleteMapping("")
    public boolean todoDelete(@RequestParam(name = "id") int id) {
        return todoService.todoDelete(id);
    }

    /** 전체 조회 + 페이징 처리 */
    @GetMapping("/page")
    public List<TodoDto> todoFindByPage(
            // defaultValue = "초기값" : 만약에 매개변수값이 존재하지 않는다면 초기값을 대입
            // page : 전체 조회할 페이지 번호 | 초기값 : 1
            @RequestParam(name = "page", defaultValue = "1") int page,
            // size : 현재 조회할 페이지당 자료의 개수 | 초기값 : 3
            @RequestParam(name = "size", defaultValue = "3") int size
    ) {
        return todoService.todoFindByPage(page, size);
    }

    /** 제목 검색 조회1 <br/>입력한 값이 ★일치★한 제목 조회 */
    @GetMapping("/search1")
    public List<TodoDto> search1(@RequestParam(name = "title") String title) {
        return todoService.search1(title);
    }

    /** 제목 검색 조회2 <br/>입력한 값이 ★포함★된 제목 조회 */
    @GetMapping("/search2")
    public List<TodoDto> search2(@RequestParam(name = "keyword") String keyword) {
        return todoService.search2(keyword);
    }

}
