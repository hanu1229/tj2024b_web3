package book.model.entity;

import book.model.dto.BookDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class BookEntity extends BaseTime {

    // 추천 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // 제목
    private String title;
    // 저자
    private String writer;
    // 간단한 소개
    private String intro;
    // 비밀번호
    private String password;

    // 양방향 통신
    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default @ToString.Exclude
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // Entity --> Dto Transform
    public BookDto toDto() {
        return BookDto.builder()
                .id(this.id).title(this.title).writer(this.writer)
                .intro(this.intro).password(this.password)
                .createDate(this.getCreateDate()).updateDate(this.getUpdateDate())
                .build();
    }

}
