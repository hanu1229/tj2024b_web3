package web.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import web.model.dto.MemberDto;

@Entity
@Table(name = "member")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String email;
    private String password;
    private String name;

    // Entity --> Dto
    public MemberDto toDto() {
        return MemberDto.builder().no(this.no).email(this.email).password(this.password).name(this.name).build();
    }

}
