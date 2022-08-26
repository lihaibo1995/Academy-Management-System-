package com.lee.www.mapper;

import com.lee.www.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李海波
 * @since 2022-07-22
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id from sys_role where flag=#{flag}")
    Integer selectByFlag(@Param("flag") String role);
}
