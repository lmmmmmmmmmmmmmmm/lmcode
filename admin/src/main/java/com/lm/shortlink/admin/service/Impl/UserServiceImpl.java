package com.lm.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.shortlink.admin.commom.convention.exception.ServiceException;
import com.lm.shortlink.admin.commom.enums.UserErrorCodeEnum;
import com.lm.shortlink.admin.dao.entity.UserDO;
import com.lm.shortlink.admin.dao.mapper.UserMapper;
import com.lm.shortlink.admin.dto.reps.UserRespDTO;
import com.lm.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;




/**
 * 用户接口实现层
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {



    @Override
    public UserRespDTO getUserByUserName(String username) {

        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ServiceException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result=new UserRespDTO();
        BeanUtils.copyProperties(userDO,result);
        return result;
    }
}
