package web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    // 관례적으로 클래스 내부에서 사용하는 필드들은 수정이 불가능한 상태로 사용한다.
    // 관례적으로 다른 곳에 해당하는 필드를 수정 불가능하도록 final 키워드를 사용한다.
    private final MemberService memberService;

    /// ● 회원가입 <br/>
    /// ● [URL](http://localhost:8080/member/signup) <br/>
    /// ● 샘플 : {"email" : "1234@naver.com", "password" : "1234", "name" : "홍길동"}
    @PostMapping("/signup")
    public boolean signup(@RequestBody() MemberDto memberDto) {
        System.out.println(">> MemberController.signup");
        System.out.println(">> memberDto = " + memberDto);
        if(memberDto.getEmail().isEmpty()) {
           return false;
        }
        if(memberDto.getPassword().isEmpty()) {
            return false;
        }
        if(memberDto.getName().isEmpty()) {
            return false;
        }
        return memberService.signup(memberDto);
    }

    /// ● 로그인 <br/>
    /// ● [URL](http://localhost:8080/member/login) <br/>
    /// ● 샘플 : {"email" : "1234@naver.com", "password" : "1234"}
    @PostMapping("/login")
    public String login(@RequestBody() MemberDto memberDto) {
        System.out.println(">> MemberController.login");
        System.out.println(">> memberDto = " + memberDto);
        return memberService.login(memberDto);
    }
    
    /// ● 로그인된 회원 검증 / 내정보 조회
    /// @RequestHeader : HTTP 헤더 정보를 매핑하는 어노테이션 | JWP 정보는 HTTP 헤더에 담을 수 있음
    /// <br/> Authorization : 인증 속성 | { Authorization : token값 }
    /// @RequestParam : HTTP 헤더의 쿼리스트링을 매핑하는 어노테이션
    /// @RequestBody : HTTP 본문의 객체를 매핑하는 어노테이션
    /// @PathVariable : HTTP 헤더의 경로 값을 매핑하는 어노테이션
    @GetMapping("/info")
    public MemberDto info(@RequestHeader("Authorization") String token) {
        System.out.println(">> MemberController.info");
        System.out.println(">> token = " + token);
        return memberService.info(token);
    }

    /// ● 로그아웃
    @GetMapping("/logout")
    public void logout(@RequestParam(name = "email") String email) {
        System.out.println(">> MemberController.logout");
        System.out.println(">> email = " + email);
        memberService.logout(email);
    }

    
}
