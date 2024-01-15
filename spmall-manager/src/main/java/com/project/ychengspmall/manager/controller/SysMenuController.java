package com.project.ychengspmall.manager.controller;

import com.project.ychengspmall.common.service.exception.YchengException;
import com.project.ychengspmall.manager.service.SysMenuService;
import com.project.ychengspmall.manager.service.SysRoleMenuService;
import com.project.ychengspmall.model.dto.system.AssginMenuDto;
import com.project.ychengspmall.model.entity.system.SysMenu;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.system.SysMenuVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/admin/system/sysMenu")
public class SysMenuController {

    @Resource
    SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateById")
    public Result updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        try {
            sysMenuService.removeById(id);
        } catch (YchengException e) {
            return Result.build(null, e.getResultCodeEnum().getCode(), e.getMessage());
        }
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result<Map<String, Object>> findSysRoleMenuByRoleId(@PathVariable(value = "roleId") Long roleId) {
        Map<String, Object> sysRoleMenuList = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(sysRoleMenuList, ResultCodeEnum.SUCCESS);


    }


    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList = sysMenuService.findUserMenuList();
        return Result.build(sysMenuVoList, ResultCodeEnum.SUCCESS);
    }
}
