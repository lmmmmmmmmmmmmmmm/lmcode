package com.lm.shortlink.project.handle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lm.shortlink.project.common.convention.result.Result;
import com.lm.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.lm.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

public class CustomBlockHandler {
    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }

}
