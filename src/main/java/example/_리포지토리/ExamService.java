package example._리포지토리;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    
    // 조작할 엔티티 리포지토리의 인터페이스
    private final ExamEntityRepository examEntityRepository;
    
    // 등록
    public boolean post(ExamEntity examEntity) {
        System.out.println(">> ExamService.post");
        System.out.println(">> examEntity = " + examEntity);
        // 현재 엔티티를 저장하기 | .save()는 연결된
        // examEntityRepository.save(저장할엔티티객체);
        ExamEntity entity = examEntityRepository.save(examEntity);
        // ↓ 영속 전 / 매핑 전 examEntity
        examEntity.setKor(5);
        // ↓ 영속 후 / 매핑 후 examEntity
        entity.setKor(10);
        // examEntityRepository.save(entity);
        return true;
    }
    // 전체조회
    public List<ExamEntity> get() {
        // 모든 엔티티를 리스트로 반환 | findAll();
        return examEntityRepository.findAll();
    }
    // 수정 | 잘 사용하지 않음
    public boolean put(ExamEntity examEntity) {
        // 현재 엔티티의 ID가 존재하면 update / 없으면 insert | .save(엔티티객체);
        ExamEntity result = examEntityRepository.save(examEntity);
        return true;
    }
    // 수정 : 존재하는 ID만 수정 | .findById(pk값);
    @Transactional
    public boolean put2(ExamEntity examEntity) {
        // id에 해당하는 엔티티 찾기
        Optional<ExamEntity> optionalExamEntity = examEntityRepository.findById(examEntity.getId());
        // 만약에 조회한 엔티티가 있다면 | .isPresent() --> true/false
        if(optionalExamEntity.isPresent()) {
            // Optional객체에서 엔티티 꺼내기
            ExamEntity entity = optionalExamEntity.get();
            entity.setName(examEntity.getName());
            entity.setKor(examEntity.getKor());
            entity.setEng(examEntity.getEng());
            return true;
        }
        return false;
    }
    // 삭제
    public boolean delete(String id) {
        examEntityRepository.deleteById(id);
        System.out.println(examEntityRepository.count());
        System.out.println(examEntityRepository.existsById(id));
        return true;
    }

}
