package web.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import web.model.entity.MemberEntity;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class MemberDto {

    private int no;
    private String email;
    private String password;
    private String name;

    // Dto --> Entity
    public MemberEntity toEntity() {
        return MemberEntity.builder().no(this.no).email(this.email).password(this.password).name(this.name).build();
    }
}
