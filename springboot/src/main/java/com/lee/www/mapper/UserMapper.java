package com.lee.www.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.www.controller.dto.UserPasswordDTO;
import com.lee.www.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李海波
 * @since 2022-07-15
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> page, @Param("username") String username,
                        @Param("email") String email, @Param("address") String address);


}