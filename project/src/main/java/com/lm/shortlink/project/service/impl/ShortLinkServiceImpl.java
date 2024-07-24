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


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.shortlink.project.common.convention.exception.ServiceException;
import com.lm.shortlink.project.dao.entity.ShortLinkDO;
import com.lm.shortlink.project.dao.mapper.ShortLinkMapper;
import com.lm.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.lm.shortlink.project.dto.req.ShortLinkBatchCreateReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkBatchCreateRespDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.lm.shortlink.project.service.ShortLinkService;
import com.lm.shortlink.project.toolkit.HashUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短链接接口实现层
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    @Autowired
    private RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        ShortLinkDO shortLinkDO= BeanUtil.toBean(requestParam,ShortLinkDO.class);
        String generateSuffix = generateSuffix(requestParam);
        String fullShortUrl=requestParam.getDomain()+"/"+generateSuffix;
        shortLinkDO.setShortUri(generateSuffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(requestParam.getDomain()+"/"+generateSuffix);
        try {
            baseMapper.insert(shortLinkDO);
        }catch (DuplicateKeyException ex){
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO!=null){
                log.warn("短链接{}重复入库",fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        shortUriCreateCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Override
    public ShortLinkCreateRespDTO createShortLinkByLock(ShortLinkCreateReqDTO requestParam) {
        return null;
    }

    @Override
    public ShortLinkBatchCreateRespDTO batchCreateShortLink(ShortLinkBatchCreateReqDTO requestParam) {
        return null;
    }

    @Override
    public void updateShortLink(ShortLinkUpdateReqDTO requestParam) {

    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0)
                .eq(ShortLinkDO::getDelFlag, 0);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each->BeanUtil.toBean(each, ShortLinkPageRespDTO.class));


    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam) {
        return null;
    }

    @Override
    public void restoreUrl(String shortUri, ServletRequest request, ServletResponse response) {

    }

    @Override
    public void shortLinkStats(ShortLinkStatsRecordDTO shortLinkStatsRecord) {

    }




    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        int customGenerateCount = 0;
        String shorUri;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl();
            originUrl+=System.currentTimeMillis();
            shorUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain()+"/"+shorUri)){
                break;
            }
            customGenerateCount++;
        }
        return shorUri;
    }
}
