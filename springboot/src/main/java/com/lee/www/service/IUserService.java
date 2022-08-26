package com.lee.www.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.www.controller.dto.UserDTO;
import com.lee.www.controller.dto.UserPasswordDTO;
import com.lee.www.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李海波
 * @since 2022-07-15
 */
public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);


    void updatePassword(UserPasswordDTO userPasswordDTO);




    Page<User> findPage(Page<User> objectPage, String username, String email, String address);
}
