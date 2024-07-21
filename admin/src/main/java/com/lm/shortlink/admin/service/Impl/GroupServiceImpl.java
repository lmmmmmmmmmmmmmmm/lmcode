package com.lm.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.shortlink.admin.dao.entity.GroupDO;
import com.lm.shortlink.admin.dao.mapper.GroupMapper;
import com.lm.shortlink.admin.dto.reps.ShortLinkGroupRespDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.lm.shortlink.admin.service.GroupService;
import com.lm.shortlink.admin.util.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid=RandomGenerator.generateRandom();
        }while(!hasGid(gid));
        GroupDO groupDO=GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .build();

            baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                //todo 设置用户名
                .eq(GroupDO::getUsername, 0);
        GroupDO groupDO = baseMapper.selectOne(queryWrapper);
        return groupDO==null;
    }

    @Override
    public void saveGroup(String username, String groupName) {

    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        return null;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {

    }

    @Override
    public void deleteGroup(String gid) {

    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {

    }
}
