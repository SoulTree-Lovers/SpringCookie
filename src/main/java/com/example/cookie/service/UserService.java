package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // login logic
    public void login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ) {
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if (optionalUser.isPresent()) {
            var userDto = optionalUser.get();

            if (userDto.getPassword().equals(pw)) {
                // cookie에 해당 정보를 저장
                var cookie = new Cookie("authorization-cookie", userDto.getId());
                cookie.setDomain("localhost"); // 도메인 지정 (naver.com , daum.net, ...)
                cookie.setPath("/");
                cookie.setMaxAge(-1); // 세션과 동일 (연결된 동안만 사용)

                /** 보안 관련 설정 */
                cookie.setHttpOnly(true);   // javascript로 쿠키값을 해킹할 수 없도록 설정
                cookie.setSecure(true);     // https에서만 사용되도록 설정
//                cookie.setMaxAge(10); // 10초 간 유효

                httpServletResponse.addCookie(cookie);

            } else {

            }

        } else {
            new RuntimeException("User not found");
        }
    }
}
