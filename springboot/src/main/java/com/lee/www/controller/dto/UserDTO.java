package com.lee.www.controller.dto;


import com.lee.www.entity.Menu;
import lombok.Data;

import java.util.List;

/*
接受前端登录请求的参数
 */

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String avatarUrl;
    private String token;
    private String role;
    private List<Menu> menus;
}
