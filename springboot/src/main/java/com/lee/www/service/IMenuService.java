package com.lee.www.service;

import com.lee.www.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李海波
 * @since 2022-07-22
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
