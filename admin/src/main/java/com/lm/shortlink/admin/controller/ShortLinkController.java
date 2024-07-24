package com.lm.shortlink.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.remote.dto.ShortLinkActualRemoteService;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkCreateRespDTO;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkPageRespDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
