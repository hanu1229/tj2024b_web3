package web.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.model.dto.MemberDto;
import web.model.entity.MemberEntity;
import web.model.repository.MemberRepository;
import web.util.JwtUtil;

@Service
@RequiredArgsConstructor
// 트랜잭션 : 여러개의 SQL을 하나의 논리 단위로 만듬
// 트랜잭션은 성공 또는 실패, 부분 성공은 없듬
// 메소드 안에서 여러가지 SQL이 실행될 경우 하나라도 오류가 발생하면 롤백(취소) * JPA 엔티티를 수정할 때는 필수
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    /** 회원가입 */
    public boolean signup(MemberDto memberDto) {
        System.out.println(">> MemberService.signup");
        System.out.println(">> memberDto = " + memberDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(memberDto.getPassword());
        System.out.println(">> hashedPassword = " + hashedPassword);
        memberDto.setPassword(hashedPassword);
        MemberEntity memberEntity = memberRepository.save(memberDto.toEntity());
        if(memberEntity.getNo() > 0) {
            System.out.println(">> 실행!");
            return true;
        }
        return false;
    }

    /** 로그인 */
    public String login(MemberDto memberDto) {
        System.out.println(">> MemberService.login");
        System.out.println(">> memberDto = " + memberDto);
        // 이메일(아이디)를 DB에서 조회하여 엔티티 찾기
        MemberEntity memberEntity = memberRepository.findByEmail(memberDto.getEmail());
        if(memberEntity == null) { return null; }
        // 조회된 엔티티의 비밀번호 검증
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isMatch = passwordEncoder.matches(memberDto.getPassword(), memberEntity.getPassword());
        // 비밀번호 검증 실패
        if(!isMatch) { return null; }
        // 비밀번호 검증 성공 | 세션 할당, 토큰 할당
        String token = jwtUtil.createToken(memberEntity.getEmail());
        System.out.println(">> 발급된 token = " + token);
        return token;
    }

    /// ● 로그인된 회원 검증 / 내정보 조회 <br/>
    /// ● 전달 받은 token으로 token을 검증하여 유효한 token으로 회원정보를 반환하고 유효하지 않은 token은 null을 반환
    public MemberDto info(String token) {
        System.out.println(">> MemberService.info");
        System.out.println(">> token = " + token);
        // 전달받은 token으로 검증하기
        String email = jwtUtil.validateToken(token);
        // 검증이 실패이면 "비로그인중"이거나 유효기간 만료
        if(email == null) { return null; }
        // 검증이 성공이면 토큰에 저장된 이메일을 가지고 엔티티 조회
        MemberEntity memberEntity = memberRepository.findByEmail(email);
        // 조회된 엔티티가 없으면 실패
        if(memberEntity == null) { return null; }
        // 조회된 엔티티가 있으면 dto로 변환하여 반환
        return memberEntity.toDto();
    }

    /// ● 로그아웃
    public void logout(String email) {
        System.out.println(">> MemberService.logout");
        System.out.println(">> email = " + email);
        jwtUtil.deleteToken(email);
    }
    

}
