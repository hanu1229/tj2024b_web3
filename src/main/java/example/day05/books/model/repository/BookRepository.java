package example.day05.books.model.repository;

import example.day05.books.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends JpaRepository<BookEntity, Integer> {



}
