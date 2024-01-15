package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.dto.system.SysUserDto;
import com.project.ychengspmall.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    List<SysUser> findByPage(SysUserDto sysUserDto);

    SysUser findByUserName(String userName);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void setDeleteById(Long userId);
}
