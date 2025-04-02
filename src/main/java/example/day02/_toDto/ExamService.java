package example.day02._toDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamService {

    private final ExamRepository examRepository;

    // dto --> entity로 변환
    public boolean post(ExamDto examDto) {
        // Dto에 영속성을 부여할 수 없고 Dto를 Entity로 변환해야함
        ExamEntity1 examEntity1 = examDto.toEntity();
        // 영속성 부여
        examRepository.save(examEntity1);
        return true;
    }

    // entity --> dto로 변환
    public List<ExamDto> get() {
        // 모든 영속된/레코드를 조회
        List<ExamEntity1> entityList = examRepository.findAll();
        // 모든 영속된 엔티티 대신에 DTO로 변환한다(스트림 사용)
        // return entityList.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
        // 심플한 방법(for문 사용)
        List<ExamDto> examDtoList = new ArrayList<>();
        for(int i = 0; i < entityList.size(); i++) {
            ExamEntity1 entity1 = entityList.get(i);
            ExamDto examDto = entity1.toDto();
            examDtoList.add(examDto);
        }
        return examDtoList;
    }

}
