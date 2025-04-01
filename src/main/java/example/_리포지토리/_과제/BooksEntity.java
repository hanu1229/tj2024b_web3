package example._리포지토리._과제;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private int id;
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String title;
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String writer;
    @Column(nullable = false, length = 50)
    private String publisher;
    @Column(nullable = false, columnDefinition = "date")
    private LocalDate year;
}
