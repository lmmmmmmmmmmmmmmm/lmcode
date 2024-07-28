package com.lm.shortlink.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkCreateRespDTO;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkGroupCountQueryRespDTO;
import com.lm.shortlink.admin.remote.dto.reps.ShortLinkPageRespDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.lm.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
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


    /**
     * 查询分组短链接总量
     *
     * @param requestParam 分组短链接总量请求参数
     * @return 查询分组短链接总量响应
     */
    default Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(List<String> requestParam){
        Map<String ,Object> requestMap=new HashMap<>();
        requestMap.put("requestParam",requestParam);
        String resultPageStr= HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count",requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }

    /**
     * 修改短链接
     * @param requestParam
     */
    default void updateShortLink(ShortLinkUpdateReqDTO requestParam){
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/update", JSON.toJSONString(requestParam));

    }


    /**
     * 根据url获取标题
     * @param url
     * @return
     */
    default Result<String> getTitleByUrl(@RequestParam("url") String url){
        String encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8); // 对url进行URL编码
        String requestUrl = "http://127.0.0.1:8001/api/short-link/v1/title?url=" + encodedUrl;
        String resultBodyStr = HttpUtil.get(requestUrl); // 使用构造好的完整URL进行请求
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {});

    }



}
