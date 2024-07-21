package com.lm.shortlink.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.shortlink.admin.dao.entity.UserDO;
import com.lm.shortlink.admin.dto.reps.UserLoginRespDTO;
import com.lm.shortlink.admin.dto.reps.UserRespDTO;
import com.lm.shortlink.admin.dto.req.UserLoginReqDTO;
import com.lm.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.lm.shortlink.admin.dto.req.UserUpdateReqDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     *根据用户名查询用户信息
     *
     */
    UserRespDTO getUserByUserName(String username);


    /**
     * 查看用户名是否可用 可用返回true 反之false
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户请求参数
     * @param userRegisterReqDTO
     */
    void register(UserRegisterReqDTO userRegisterReqDTO);


    /**
     * 根据用户名修改请求参数
     * @param userUpdateReqDTO
     */
    void update(UserUpdateReqDTO userUpdateReqDTO);

    /**
     * 用户登录
     * @param userLoginReqDTO
     * @return
     */
    UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO);


    /**
     * 检查用户是否登录
     *
     * @param username 用户名
     * @param token    用户登录 Token
     * @return 用户是否登录标识
     */
    Boolean checkLogin(String username, String token);
}
