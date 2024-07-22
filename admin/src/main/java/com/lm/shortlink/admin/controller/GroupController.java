package com.lm.shortlink.admin.controller;

import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.commom.convention.result.Results;
import com.lm.shortlink.admin.dto.reps.ShortLinkGroupRespDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.lm.shortlink.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup(){
        return Results.success( groupService.listGroup());
    }

    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> update(ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO){

        groupService.updateGroup(shortLinkGroupUpdateReqDTO);
        return Results.success();


    }


}
