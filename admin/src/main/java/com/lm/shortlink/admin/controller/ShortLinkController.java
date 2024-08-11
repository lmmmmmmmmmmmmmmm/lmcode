package com.lm.shortlink.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.commom.convention.result.Results;
import com.lm.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkBaseInfoRespDTO;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkBatchCreateRespDTO;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkCreateRespDTO;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkPageRespDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkBatchCreateReqDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.lm.shortlink.admin.toolkit.EasyExcelWebUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShortLinkController {

    ShortLinkActualRemoteService shortLinkActualRemoteService=new ShortLinkActualRemoteService() {};


    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkActualRemoteService.pageShortLink(requestParam);
    }


    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkActualRemoteService.createShortLink(requestParam);
    }

    /**
     * 批量创建短链接
     */
    @SneakyThrows
    @PostMapping("/api/short-link/admin/v1/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkActualRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response, "批量创建短链接-SaaS短链接系统", ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }


    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkActualRemoteService.updateShortLink(requestParam);
        return Results.success();
    }









}
