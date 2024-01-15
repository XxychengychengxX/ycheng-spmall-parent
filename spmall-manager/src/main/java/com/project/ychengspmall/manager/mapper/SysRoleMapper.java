package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.dto.system.SysRoleDto;
import com.project.ychengspmall.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper  {

    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);
    void setDeleteById(Long roleId);

    List<SysRole> findAllRoles();

}
