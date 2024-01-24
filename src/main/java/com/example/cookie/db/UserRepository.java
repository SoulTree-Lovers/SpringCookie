package com.example.cookie.db;

import com.example.cookie.model.UserDto;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {

    private final List<UserDto> userDtoList = new ArrayList<>();

    public Optional<UserDto> findById(String id) {
        return userDtoList.stream()
                .filter(it -> {
                    return it.getId().equals(id);
                })
                .findFirst();
    }

    public Optional<UserDto> findByName(String name) {
        return userDtoList.stream()
                .filter(it -> {
                    return it.getName().equals(name);
                })
                .findFirst();
    }

    @PostConstruct
    public void init() {
        userDtoList.add(new UserDto(UUID.randomUUID().toString(), "강승민", "1234"));
        userDtoList.add(new UserDto(UUID.randomUUID().toString(), "최민석", "1234"));
        userDtoList.add(new UserDto(UUID.randomUUID().toString(), "강진호", "1234"));
    }
}
