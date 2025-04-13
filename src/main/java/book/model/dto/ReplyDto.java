package book.model.dto;

import book.model.entity.ReplyEntity;
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
public class ReplyDto {

    // 리뷰 번호
    private int id;
    // 내용
    private String comment;
    // 비밀번호
    private String password;
    // 추천 번호 ( FK )
    private int bookId;
    // 생성일
    private LocalDateTime createDate;
    // 수정일
    private LocalDateTime updateDate;

    // Dto --> Entity Transform
    public ReplyEntity toEntity() {
        return ReplyEntity.builder()
                .id(this.id).comment(this.comment).password(this.password)
                .build();
    }

}
