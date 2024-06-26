package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,
            @CookieValue(required = false, name = "authorization-cookie") String authorizationCookie
    ) {
        log.info("authorizationCookie: {}", authorizationCookie);

        var optionalUserDto = userRepository.findById(authorizationCookie);

        if (optionalUserDto.isPresent()) {
            return optionalUserDto.get();
        }
        return null;

//        var cookies = httpServletRequest.getCookies();
//
//        if (cookies != null) {
//
//            for (Cookie cookie : cookies) {
//                log.info("key: {}, value: {}", cookie.getName(), cookie.getValue());
//            }
//        }

//        return null;
    }

    @GetMapping("/me2")
    public UserDto me2(
            @RequestHeader(name = "authorization", required = false) String authorizationHeader
    ) {
        log.info("authorizationHeader: {}", authorizationHeader);

//        userRepository.printUserDtoList();

        var optionalUserDto = userRepository.findById(authorizationHeader);

        if (optionalUserDto.isPresent()) {
            return optionalUserDto.get();
        }

        return null;
    }
}
