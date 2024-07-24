package com.lm.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lm.shortlink.project.common.convention.result.Result;
import com.lm.shortlink.project.common.convention.result.Results;
import com.lm.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.lm.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.lm.shortlink.project.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;



@RestController
public class ShortLinkController {

    @Autowired
    private ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return  Results.success(shortLinkService.pageShortLink(requestParam));
    }


    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;







}
