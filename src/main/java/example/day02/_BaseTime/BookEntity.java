package example.day02._BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "day02book")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class BookEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int 도서번호;
    @Column(nullable = false, length = 100)
    private String 도서명;
    @Column(nullable = false, length = 30)
    private String 저자;
    @Column(nullable = false, length = 50)
    private String 출판사;
    @Column(nullable = false)
    private int 출판년도;
}
