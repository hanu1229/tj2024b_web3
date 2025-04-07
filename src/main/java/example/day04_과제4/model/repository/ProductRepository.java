package example.day04_과제4.model.repository;

import example.day04_과제4.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    /** 비품 등록 */
    @Modifying
    @Query(value = "insert into task04(name, description, quantity, create_date, update_date) " +
            "values(:name, :description, :quantity, :createDate, :updateDate)", nativeQuery = true)
    int saveNative(
            @Param("name") String name,
            @Param("description") String description,
            @Param("quantity") int quantity,
            @Param("createDate") String createDate,
            @Param("updateDate") String updateDate
    );

    /** 비품 전체 조회 */
    @Query(value = "select * from task04", nativeQuery = true)
    List<ProductEntity> findAllNative();

    /** 비품 개별 조회 + 비품 수정 기능 */
    @Query(value = "select * from task04 where id = :id", nativeQuery = true)
    Optional<ProductEntity> findByIdNative(int id);

    /** 비품 삭제 기능 */
    @Modifying
    @Query(value = "delete from task04 where id = :id", nativeQuery = true)
    void deleteByIdNative(int id);

}
