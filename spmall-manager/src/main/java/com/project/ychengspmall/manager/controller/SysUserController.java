package com.project.ychengspmall.manager.controller;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.common.log.annotation.Log;
import com.project.ychengspmall.manager.service.SysUserService;
import com.project.ychengspmall.model.dto.system.AssignRoleDto;
import com.project.ychengspmall.model.dto.system.SysUserDto;
import com.project.ychengspmall.model.entity.system.SysRole;
import com.project.ychengspmall.model.entity.system.SysUser;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService ;

    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(SysUserDto sysUserDto ,
                                                @PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    @PostMapping(value = "/saveSysUser")
    @Log(title = "新增用户",businessType = 0)
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping(value = "/deleteById/{userId}")
    public Result deleteById(@PathVariable(value = "userId") Long userId) {
        sysUserService.deleteById(userId) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }


    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignRoleDto assginRoleDto) {
        sysUserService.doAssign(assginRoleDto) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
