package book.model.dto;

import book.model.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class BookDto {

    // 추천 번호
    private int id;
    // 제목
    private String title;
    // 저자
    private String writer;
    // 간단한 소개
    private String intro;
    // 비밀번호
    private String password;
    // 생성일
    private LocalDateTime createDate;
    // 수정일
    private LocalDateTime updateDate;

    // Dto --> Entity Transfrom
    public BookEntity toEntity() {
        return BookEntity.builder()
                .id(this.id).title(this.title).writer(this.writer)
                .intro(this.intro).password(this.password)
                .build();
    }

}
