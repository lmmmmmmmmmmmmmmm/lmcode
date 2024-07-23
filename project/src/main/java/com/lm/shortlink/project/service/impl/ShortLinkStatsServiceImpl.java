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
import com.lm.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkStatsRespDTO;
import com.lm.shortlink.project.service.ShortLinkStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 短链接监控接口实现层
 *
 */
@Service
@RequiredArgsConstructor
public class ShortLinkStatsServiceImpl implements ShortLinkStatsService {


    @Override
    public ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return null;
    }

    @Override
    public ShortLinkStatsRespDTO groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return null;
    }

    @Override
    public IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return null;
    }

    @Override
    public IPage<ShortLinkStatsAccessRecordRespDTO> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return null;
    }
}
