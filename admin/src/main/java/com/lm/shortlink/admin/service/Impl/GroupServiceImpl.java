package com.lm.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.shortlink.admin.dao.entity.GroupDO;
import com.lm.shortlink.admin.dao.mapper.GroupMapper;
import com.lm.shortlink.admin.dto.reps.ShortLinkGroupRespDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.lm.shortlink.admin.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {

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
