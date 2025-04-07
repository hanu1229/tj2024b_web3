package example.day04.model.repository;

import example.day04.model.entity.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    // JPA Repository
    /*
        1. .save(); .findById(); .findAll(); .deleteById(); 등등 미리 만들어진 CRUD 메소드를 제공
        2. 쿼리메소드(JPQL을 이용한 메소드 이름 기반으로 자동 생성)
        3. 네이티브쿼리(SQL에 직접 작성)
    */

    // 쿼리메소드(JPQL을 이용한 메소드 이름 기반으로 자동 생성)
    // Spring JPA에서 SQL문장을 직접 작성하지 않고 메소드 이름으로 쿼리를 생성하는 방법(무조건 카멜표기법 사용)
    // 메소드의 명명 규칙을 따라야함
    // findBy필드명 : 조회
    List<TodoEntity> findByTitle(String title);
    // ├─ List<TodoEntity> : 조회 결과를 List타입으로 반환
    // ├─ findByTitle : title필드를 select(조회)한다. ※ 무조건 카멜표기법으로 작성
    // ├─ (String title) : 조회 조건
    // └─ myBatis : select * from todo where title = ${title}
    // findBy필드명Containing : like를 사용한 조회
    List<TodoEntity> findByTitleContaining(String keyword);
    // └─ myBatis : select * from todo where title like %${keyword}%
    // findBy필드명And필드명 : 두 조건을 조회 | And, Or
    List<TodoEntity> findByTitleAndContent(String title, String content);
    // existsBy필드명 : 조건에 맞는 엔티티 여부(true/false) 조회 | 반환타입 : boolean | And, Or 가능
    boolean existsByTitle(String title);
    // countBy필드명 : 조건에 맞는 엔티티 개수 조회 | 반환타입 : long | And, Or 가능
    long countByTitle(String title);
    // deleteBy필드명 : 조건에 맞는 엔티티를 삭제 | 반환타입 : void | And, Or 가능
    void deleteByTitle(String title);
    
    // 네이비브 쿼리
    // Spring JPA에서 SQL문법을 직접 작성하여 실행
    // @Query(value = "sql문", nativeQuery = true) | @Query는 select를 위한 어노테이션
    // insert, update, delete를 할 경우에는 @Modifying을 같이 사용
    // SQL문의 매개변수를 작성 시에는 :매개변수명으로 작성하여 매개변수를 대입할 수 있다.
    @Query(value = "select * from todo where title = :title", nativeQuery = true)
    List<TodoEntity> findByTitleNative(String title);
    // ├─ List<TodoEntity> : 조회 결과를 List타입으로 반환
    // ├─ findByTitleNative : 규칙이 없으므로 아무렇게나 작성 가능
    // └─ (String title) : 조회 조건으로 SQL문법의 매개변수
    @Query(value = "select * from todo where title like %:keyword%", nativeQuery = true)
    List<TodoEntity> findByTitleNativeSearch(String keyword);
    // delete
    @Modifying
    @Query(value = "delete from todo where title = :title", nativeQuery = true)
    boolean deleteByNative(String title);
    // update
    @Modifying
    @Query(value = "update todo set title = :title where id = :id", nativeQuery = true)
    TodoEntity updateByNative(int id, String title);

}
