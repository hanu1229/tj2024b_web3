package web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /** 회원가입 */
    @PostMapping("")
    public boolean signup(@RequestBody() MemberDto memberDto) {
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
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

}
