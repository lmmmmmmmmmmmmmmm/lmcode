package com.lm.shortlink.admin.controller;

import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.commom.convention.result.Results;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.lm.shortlink.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 短链接分组控制层
 */
@RestController
//@RequiredArgsConstructor 构造方法式注入
public class GroupController {
    @Autowired
    private GroupService groupService;


    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> sava(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO){
        groupService.saveGroup(shortLinkGroupSaveReqDTO.getName());
        return Results.success();
    }


}
