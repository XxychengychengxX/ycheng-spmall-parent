package com.project.ychengspmall.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.common.service.exception.YchengException;
import com.project.ychengspmall.manager.mapper.SysRoleUserMapper;
import com.project.ychengspmall.manager.mapper.SysUserMapper;
import com.project.ychengspmall.manager.service.SysUserService;
import com.project.ychengspmall.model.dto.system.AssignRoleDto;
import com.project.ychengspmall.model.dto.system.LoginDto;
import com.project.ychengspmall.model.dto.system.SysUserDto;
import com.project.ychengspmall.model.entity.system.SysUser;
import com.project.ychengspmall.model.entity.user.UserInfo;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.system.LoginVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, UserInfo> implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    StringRedisTemplate redisTemplate;
    @Resource
    SysRoleUserMapper sysRoleUserMapper;

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.setDeleteById(userId);
    }

    @Transactional
    @Override
    public void doAssign(AssignRoleDto assignRoleDto) {

        // 删除之前的所有的用户所对应的角色数据
        sysRoleUserMapper.deleteByUserId(assignRoleDto.getUserId());

        // 分配新的角色数据
        List<Long> roleIdList = assignRoleDto.getRoleIdList();
        roleIdList.forEach(roleId -> {
            sysRoleUserMapper.doAssign(assignRoleDto.getUserId(), roleId);
        });
    }

    @Override
    public void saveSysUser(SysUser sysUser) {

        // 根据输入的用户名查询用户
        SysUser dbSysUser = sysUserMapper.findByUserName(sysUser.getUserName());
        if (dbSysUser != null) {
            throw new YchengException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        // 对密码进行加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(0);
        sysUserMapper.saveSysUser(sysUser);
    }

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        PageInfo pageInfo = new PageInfo(sysUserList);
        return pageInfo;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);

    }

    @Override
    public SysUser getUserInfo(String token) {
        String s = redisTemplate.opsForValue().get(token);
        return JSON.parseObject(s, SysUser.class);
    }

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key

        // 从Redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:" + codeKey);
        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)) {
            throw new YchengException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        // 验证通过删除redis中的验证码
        redisTemplate.delete("user:login:validatecode:" + codeKey);

        // 根据用户名查询用户
        QueryWrapper<UserInfo> query = new QueryWrapper<UserInfo>().eq("username", loginDto.getUserName());
        UserInfo userInfo = sysUserMapper.selectOne(query);
        if (userInfo == null) {
            throw new RuntimeException("用户名或者密码错误");
        }

        // 验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if (!md5InputPassword.equals(userInfo.getPassword())) {
            throw new RuntimeException("用户名或者密码错误");
        }

        // 生成令牌，保存数据到Redis
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        // 返回
        return loginVo;
    }
}
