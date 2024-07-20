package com.lm.shortlink.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.shortlink.admin.dao.entity.UserDO;
import com.lm.shortlink.admin.dto.reps.UserRespDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     *根据用户名查询用户信息
     *
     */
    UserRespDTO getUserByUserName(String username);
}
