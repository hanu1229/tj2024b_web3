package web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    // (1) JPA가 기본적인 CRUD를 제공 | 단, 상속을 받아야함
    // (2) 개발자가 정의한 쿼리메소드 : 메소드 명명 규칙
    // (3) 개발자가 정의한 네이티브 쿼리 : SQL 직접 작성

    /**
        로그인
        <br/>
        추상메소드를 이용한 email로 엔티티를 조회하는 쿼리 메소드
     */
    MemberEntity findByEmail(String email);
}
