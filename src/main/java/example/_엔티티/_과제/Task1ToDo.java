package example._엔티티._과제;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Task1ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private int id;
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String title;
    @Column(nullable = false, columnDefinition = "boolean")
    private boolean state;
    @Column(nullable = false)
    private LocalDate createat;
    @Column(nullable = false, insertable = false)
    private LocalDateTime updateat;
}
