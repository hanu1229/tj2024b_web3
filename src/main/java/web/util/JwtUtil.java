package web.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// ↓ Spring 컨테이너에 빈 등록
@Component
public class JwtUtil {

    // 비밀키 알고리즘 : HS256알고리즘, HS512알고리즘
    // private String key = "인코딩된 HS512 비트키";
    // (1) 개발자가 임의로 지정한 키 : "qP9sLxV3tRzWn8vMbKjUyHdGcTfEeXcZwAoLpNjMqRsTuVyBxCmZkYhGjFlDnEpQzFgXt9pMwX8Sx7CtQ5VtBvKmA2QwE3D";
    // (2) 라이브러리를 이용한 임의 키 : import java.security.Key;
    // Keys.secretKeyFor(SignatureAlgorithm.암고리즘명);
    private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    /// 01. JWT 토큰 발급 <br/>
    /// 사용자의 이메일을 받아서 토큰 만들기
    public String createToken(String email) {
        return Jwts.builder()
                // 토큰에 넣을 내용물 | 로그인 성공한 회원의 이메일 추가
                .setSubject(email)
                // 토큰이 발급된 날짜 | 현재 날짜 추가
                .setIssuedAt(new Date())
                // 토큰 만료 시간 | 밀리(1000/1)초 + new Date(System.currentTimeMillis()) : 현재 시간 밀리초
                // new Date(System.currentTimeMillis() + (1000 * 초 * 분 * 시))
                .setExpiration( new Date( System.currentTimeMillis() + ( 1000 * 60 * 60 * 24 ) ) )
                // 지정한 비밀키로 암호화
                .signWith(secretKey)
                // 위 정보로 JWT토큰을 생성하고 반환
                .compact();
    }

    /// 02. JWT 토큰 검증
    public String validateToken(String token) {
        try {
            // parser() : 토큰을 검증하기 위한 함수
            Claims claims =  Jwts.parser()
                    // 검증하기 위한 비밀키 추가
                    .setSigningKey(secretKey)
                    // 검증을 실행할 객체 생성
                    .build()
                    // 검증된 토큰 해석 | 실패시 예외 발생
                    .parseClaimsJws(token)
                    // 검증된 claims 객체 생성
                    .getBody();
            // claims 안에는 다양한 토큰 정보가 들어있음
            // 토큰에 저장된 (로그인된)회원 이메일 출력
            System.out.println(claims.getSubject());
            return claims.getSubject();
        } catch(ExpiredJwtException e) {
            // 토큰이 만료 되었을 때 예외 클래스
            System.out.println(">> JWT 토큰 기간 만료 : " + e);
        } catch(JwtException e) {
            // 그외 모든 토큰 예외 클래스
            System.out.println(">> JWT 예외 : " + e);
        }
        // 유효하지 않은 토큰 또는 오류 발생 시 null 반환
        return null;
    }


}
