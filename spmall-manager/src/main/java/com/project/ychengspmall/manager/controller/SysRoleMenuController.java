package com.project.ychengspmall.manager.controller;

import com.project.ychengspmall.manager.service.SysRoleMenuService;
import com.project.ychengspmall.model.dto.system.AssginMenuDto;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable(value = "roleId") Long roleId) {
        Map<String, Object> sysRoleMenuList = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(sysRoleMenuList, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
