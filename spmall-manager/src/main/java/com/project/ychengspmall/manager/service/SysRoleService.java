package com.project.ychengspmall.manager.service;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.dto.system.SysRoleDto;
import com.project.ychengspmall.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> findAllRoles(Long userId);

    Map<String, Object> findAllRoles();

}
