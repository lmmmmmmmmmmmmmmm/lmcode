/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lm.shortlink.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.shortlink.project.dao.entity.ShortLinkDO;
import com.lm.shortlink.project.dao.mapper.ShortLinkMapper;
import com.lm.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.lm.shortlink.project.dto.req.RecycleBinRemoveReqDTO;
import com.lm.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.lm.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 回收站管理接口实现层
 *
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements RecycleBinService {


    @Override
    public void saveRecycleBin(RecycleBinSaveReqDTO requestParam) {
        
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return null;
    }

    @Override
    public void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam) {

    }

    @Override
    public void removeRecycleBin(RecycleBinRemoveReqDTO requestParam) {

    }
}
