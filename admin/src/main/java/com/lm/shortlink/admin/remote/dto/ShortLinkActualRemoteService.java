package com.lm.shortlink.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkCreateRespDTO;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkPageRespDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;

import java.util.HashMap;
import java.util.Map;

public interface ShortLinkActualRemoteService {


    /**\
     * 短链接创建
     * @param requestParam
     * @return
     */

    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam){
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {});

    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */

    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        Map<String ,Object> requestMap=new HashMap<>();
        requestMap.put("gid",requestParam.getGid());
        requestMap.put("current",requestParam.getCurrent());
        requestMap.put("size",requestParam.getSize());
        String resultPageStr= HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page",requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }



}
