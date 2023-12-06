package com.project.ychengspmall.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {

    void doAssign(@Param("userId") Long userId,
                  @Param("roleId") Long roleId);        // 添加关联关系

    void deleteByUserId(Long userId);                // 根据用户的id删除数据

    List<Long> findSysUserRoleByUserId(Long userId);
}
