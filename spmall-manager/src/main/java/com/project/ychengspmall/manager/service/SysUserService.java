package com.project.ychengspmall.manager.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.dto.system.AssignRoleDto;
import com.project.ychengspmall.model.dto.system.LoginDto;
import com.project.ychengspmall.model.dto.system.SysUserDto;
import com.project.ychengspmall.model.entity.system.SysUser;
import com.project.ychengspmall.model.entity.user.UserInfo;
import com.project.ychengspmall.model.vo.system.LoginVo;

public interface SysUserService extends IService<UserInfo> {
    /**
     * 用户登录
     *
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);

    /**
     * 根据请求头token获取用户信息
     *
     * @param token
     * @return
     */
    SysUser getUserInfo(String token);

    /**
     * 用户登出
     *
     * @param token 预删除的token
     */
    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);

    void doAssign(AssignRoleDto assignRoleDto);
}

