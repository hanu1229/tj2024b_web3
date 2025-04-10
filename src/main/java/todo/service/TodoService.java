package todo.service;

import todo.model.dto.TodoDto;
import todo.model.entity.TodoEntity;
import todo.model.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    /** 개별 등록 */
    @PostMapping("")
    public TodoDto todoSave(TodoDto todoDto) {
        // Dto --> Entity Transform
        TodoEntity todoEntity = todoDto.toEntity();
        // Entity를 save
        TodoEntity saveEntity = todoRepository.save(todoEntity);
        if(saveEntity.getId() > 0) {
            // saveEntity의 결과값이 존재하면
            return saveEntity.toDto();
        } else {
            return null;
        }
    }

    /** 전체 조회 */
    @GetMapping("")
    public List<TodoDto> todoFindAll() {
        // 모든 Entity를 조회
        List<TodoEntity> todoEntityList = todoRepository.findAll();
        // Entity --> Dto Transform
        // for 방법
        List<TodoDto> todoDtoList = new ArrayList<>();
        /*
        for(int index = 0; index < todoEntityList.size(); index++) {
            TodoDto dto = todoEntityList.get(index).toDto();
            todoDtoList.add(dto);
        }
        return todoDtoList;
        */
        // stream 방법
        return todoRepository.findAll().stream()
                .map(todoEntity -> { return todoEntity.toDto(); })
                .collect(Collectors.toList());
    }

    /** 개별 조회 */
    @GetMapping("/view")
    public TodoDto todoFindById(int id) {
        // id 값으로 entity 조회
        // Optional<> 클래스 : null을 제어하는 메소드들을 제공하는 클래스
        // 방법1
        /*
        Optional<TodoEntity> optional = todoRepository.findById(id);
        if(optional.isPresent()) {
            // 조회한 결과가 존재하면
            TodoEntity todoEntity = optional.get();
            TodoDto todoDto = todoEntity.toDto();
            return todoDto;
        }
        return null;
        */
        // 방법2
        return todoRepository.findById(id)
                // Optional의 데이터가 null이 아니면 실행
                .map(todoEntity -> { return todoEntity.toDto(); })
                // Optional의 데이터가 null이면
                .orElse(null);
    }

    /** 개별 수정 <br/> @Transactional 필요 */
    public TodoDto todoUpdate(TodoDto todoDto) {
        // 수정할 Entity 조회
        // 방법1
        /*
        Optional<TodoEntity> optional = todoRepository.findById(todoDto.getId());
        if(optional.isPresent()) {
            TodoEntity todoEntity = optional.get();
            todoEntity.setTitle(todoDto.getTitle());
            todoEntity.setContent(todoDto.getContent());
            todoEntity.setDone(todoDto.isDone());
            return todoEntity.toDto();
        }
        return null;
        */
        // 방법2
        return todoRepository.findById(todoDto.getId())
                .map(todoEntity -> {
                    todoEntity.setTitle(todoDto.getTitle());
                    todoEntity.setContent(todoDto.getContent());
                    todoEntity.setDone(todoDto.isDone());
                    return todoEntity.toDto();
                })
                .orElse(null);
    }

    /** 개별 삭제 */
    public boolean todoDelete(int id) {
        // id 값으로 Entity 조회
        // findById는 반환타입 Optional, existById의 반환타입은 boolean
        // 방법1
        /*
        boolean result = todoRepository.existsById(id);
        if(result) {
            // 만약 존재한다면 영속성(레코드) 제거
            todoRepository.deleteById(id);
            return true;
        }
        return false;
         */
        // 방법2
        return todoRepository.findById(id)
                .map((entity) -> {
                    todoRepository.deleteById(id);
                    return true;
                }).orElse(false);
    }

    /** 전체 조회 + 페이징 처리 */
    public List<TodoDto> todoFindByPage(int page, int size) {
        // page : 현재 조회중인 페이지 번호 | size : 페이지 1개당 조회할 자료의 개수
        // PageRequest 클래스 | .of(조회할 페이지 번호 - 1, 자료의 개수, 정렬) | 페이징처리 설정
        // - 조회할 페이지 번호는 1페이지가 0부터 시작이라 -1
        // - 페이지당 조회할 자료 개수
        // - 정렬은 선택
        //      Sort.by(Sort.Direction.ASC, 정렬할 필드명) : 오름차순
        //      Sort.by(Sort.Direction.DESC, 정렬할 필드명) : 내림차순(최신순)
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        // pageRequest 객체를 findXXX()의 매개변수로 대입
        // 방법1
        Page <TodoEntity> todoEntityPage = todoRepository.findAll(pageRequest);
        System.out.println("todoEntityPage = " + todoEntityPage);
        // .getTotalElements() : 전체 게시물 수 반환
        System.out.println("전체 게시물 수 반환 = " + todoEntityPage.getTotalElements());
        // .getTotalPages() : 전체 페이지수 반환
        System.out.println("전체 페이지수 = " + todoEntityPage.getTotalPages());
        // .getContent() : page타입을 List타입으로 변환
        System.out.println(todoEntityPage.getContent());
        // 방법1 - page타입 Entity를 Dto로 변환
        List<TodoDto> todoDtoList = new ArrayList<>();
        for(int index = 0; index < todoEntityPage.getSize(); index++) {
            TodoDto todoDto = todoEntityPage.getContent().get(index).toDto();
            todoDtoList.add(todoDto);
        }
        return todoDtoList;
        // 방법2 - stream
        /*
        return todoRepository.findAll(pageRequest).stream()
                // .map(todoEntity -> {return todoEntity.toDto();})
                .map(TodoEntity::toDto)
                .collect(Collectors.toList());
         */
    }

    /** 제목 검색 조회1 <br/>입력한 값이 ★일치★한 제목 조회 */
    public List<TodoDto> search1(String title) {
        // 방법1 - JPA Repository에서 내가 만든 추상메소드를 사용
        /*
        return todoRepository.findByTitle(title).stream()
                .map(TodoEntity::toDto).collect(Collectors.toList());
         */
        // 방법2
        return todoRepository.findByTitleNative(title).stream()
                .map(TodoEntity::toDto).collect(Collectors.toList());
    }

    /** 제목 검색 조회2 <br/>입력한 값이 ★포함★된 제목 조회 */
    public List<TodoDto> search2(String keyword) {
        // 방법1 - JPA Repository에서 내가 만든 추상메소드를 사용
        /*
        return todoRepository.findByTitleContaining(keyword).stream()
                .map(TodoEntity::toDto).collect(Collectors.toList());
         */
        // 방법2
        return todoRepository.findByTitleNativeSearch(keyword).stream()
                .map(TodoEntity::toDto).collect(Collectors.toList());
    }

}
