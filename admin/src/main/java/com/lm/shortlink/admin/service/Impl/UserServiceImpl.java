package com.lm.shortlink.admin.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.shortlink.admin.commom.convention.exception.ClientException;
import com.lm.shortlink.admin.commom.convention.exception.ServiceException;
import com.lm.shortlink.admin.commom.enums.UserErrorCodeEnum;
import com.lm.shortlink.admin.dao.entity.UserDO;
import com.lm.shortlink.admin.dao.mapper.UserMapper;
import com.lm.shortlink.admin.dto.reps.UserRespDTO;
import com.lm.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.lm.shortlink.admin.service.UserService;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lm.shortlink.admin.commom.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static com.lm.shortlink.admin.commom.enums.UserErrorCodeEnum.USER_SAVE_ERROR;


/**
 * 用户接口实现层
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private RBloomFilter<String> rBloomFilter;

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



    @Override
    public Boolean hasUsername(String username) {

        return !rBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        if (!hasUsername(userRegisterReqDTO.getUsername())){
            throw new ClientException(USER_NAME_EXIST);
        }
        int insert = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
        if (insert<1){
            throw new ClientException(USER_SAVE_ERROR);
        }
        rBloomFilter.add(userRegisterReqDTO.getUsername());
    }
}
