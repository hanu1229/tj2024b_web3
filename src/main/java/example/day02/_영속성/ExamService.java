package example.day02._영속성;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @PersistenceContext
    // ↑ 영속성들이 저장된 메모리에 객체를 주입할 수 있게 해주는 어노테이션
    // ↓ 엔티티 매니저를 이용한 영속성 조작
    private EntityManager entityManager;

    public void get() {
        // ↓ 비영속 상태
        ExamEntity examEntity1 = new ExamEntity();
        examEntity1.setName("유재석");
        System.out.println("★비영속상태 = " + examEntity1);
        // ↑ 비영속 상태

        // ↓ 영속 상태 | .persis(객체); --> 영속성을 부여함
        entityManager.persist(examEntity1);
        System.out.println("★영속상태 = " + examEntity1);
        examEntity1.setName("강호동");
        System.out.println("★영속상태 = " + examEntity1);
        // 트랜젝션 중간에 commit
        entityManager.flush();
        // ↑ 영속 상태

        // ↓ 준 영속 상태(Detached) | .detach(객체); --> 영속성 부여 취소
        entityManager.detach(examEntity1);
        System.out.println("★준영속상태 = " + examEntity1);
        examEntity1.setName("신동엽");
        entityManager.flush();
        System.out.println("★준영속상태 = " + examEntity1);
        // ↑ 준 영속 상태(Detached)

        // ↓ 영속성 다시 연결(merge) | .merge(객체); --> 영속성 다시 부여
        ExamEntity result = entityManager.merge(examEntity1);
        // ↑ 영속성 다시 연결(merge)


        // ↓ 영속 상태 취소(remove) | .remove(객체); --> 레코드까지 삭제
        entityManager.remove(result);
        // ↑ 영속 상태 취소(remove)

    }

}
