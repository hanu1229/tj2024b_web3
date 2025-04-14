package web.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.model.dto.MemberDto;
import web.model.entity.MemberEntity;
import web.model.repository.MemberRepository;

@Service
@RequiredArgsConstructor
// 트랜잭션 : 여러개의 SQL을 하나의 논리 단위로 만듬
// 트랜잭션은 성공 또는 실패, 부분 성공은 없듬
// 메소드 안에서 여러가지 SQL이 실행될 경우 하나라도 오류가 발생하면 롤백(취소) * JPA 엔티티를 수정할 때는 필수
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean signup(MemberDto memberDto) {
        System.out.println("MemberService.signup");
        System.out.println("memberDto = " + memberDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(memberDto.getPassword());
        System.out.println("hashedPassword = " + hashedPassword);
        memberDto.setPassword(hashedPassword);
        MemberEntity memberEntity = memberRepository.save(memberDto.toEntity());
        if(memberEntity.getNo() > 0) {
            System.out.println("실행!");
            return true;
        }
        return false;
    }

}
