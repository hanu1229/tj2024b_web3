package example._리포지토리;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ↓ 스프링 컨테이너에 빈 등록
@Repository
// ↓ 엔티티(테이블)을 조작(DML : insert, select, update, delete)하는 인터페이스 | 기본 CRUD는 지원
// ↓ 해당 인터페이스에 JPA엔티티 상속 --> JapRepository<조작할 엔티티 클래스명, 해당 엔티티의 ID타입 >
public interface ExamEntityRepository extends JpaRepository<ExamEntity, String> {


    /*
        CRUD 메소드
        1. .save(저장할엔티티객체);
            : 존재하지 않는 PK이면 insert, 존재하는 PK이면 update
            반환값 : insert/update 이후 영속(연결/매핑)된 객치(엔티티)
        2. .findAll();
            : 모든 엔티티를 select한다
            반환값 : 리스트타입으로 반환
        3. .findById(조회할 PK값)
            : pk값과 일치하는 엔티티를 select 한다.
            반환값 : Optional<엔티티>
        4. .deleteById(삭제할pk값)
            : pk값과 일치하는 엔티티를 delete 한다.
            반환값 : void
        5. .count()
            : 레코드(엔티티) 전체 개수 반환
            반환값 : long
        6. .existsById(조회할 pk값)
            : pk값과 일치하는 엔티티가 존재하면 true 아니면 false
    */
    // Optional클래스 : null과 관련된 메소드를 제공하는 클래스
    // -> nullPointerException을 방지하고자 객체를 포장하는 클래스
    // 주요 메소드
    // 1. .isPresent(); | null 이면 false, 객체가 있으면 true
    // 2. .get(); | 객체를 반환
    // 3. .orElse(null일때 값); | 객체를 반환하는데 null이면 지정된 값을 반환
    // 4. .orElseThrow(예외객체); | 객체를 반환하는데 null이면 예외 처리(발생)


}
