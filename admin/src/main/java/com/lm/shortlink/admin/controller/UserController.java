package com.lm.shortlink.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.commom.convention.result.Results;
import com.lm.shortlink.admin.dto.reps.UserActualRespDTO;
import com.lm.shortlink.admin.dto.reps.UserRespDTO;
import com.lm.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.lm.shortlink.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUserName(username));
    }
    /**
     * 根据用户名查询无脱敏用户信息
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUserName(username), UserActualRespDTO.class));
    }


    /**
     * 查询用户名是否存在 是否可用
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username){
        return Results.success(userService.hasUsername(username));
    }


    /**
     * 用户注册
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }


}
