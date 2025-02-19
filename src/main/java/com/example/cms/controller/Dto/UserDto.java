package com.example.cms.controller.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private long userId;
    private String name;
    private String password;
    private String email;
}
