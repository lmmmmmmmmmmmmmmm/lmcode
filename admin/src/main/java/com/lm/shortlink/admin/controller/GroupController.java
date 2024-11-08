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

package com.lm.shortlink.admin.controller;

import com.lm.shortlink.admin.commom.convention.result.Result;
import com.lm.shortlink.admin.commom.convention.result.Results;
import com.lm.shortlink.admin.dto.reps.ShortLinkGroupRespDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.lm.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.lm.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> sava(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO) {
        groupService.saveGroup(shortLinkGroupSaveReqDTO.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> update(@RequestBody ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO) {

        groupService.updateGroup(shortLinkGroupUpdateReqDTO);
        return Results.success();
    }

    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> delete(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    @PostMapping("/api/short-link/admin/v1/group/sort")
    public Result<Void> delete(@RequestBody List<ShortLinkGroupSortReqDTO> shortLinkGroupSortReqDTO) {
        groupService.sortGroup(shortLinkGroupSortReqDTO);
        return Results.success();
    }

}
