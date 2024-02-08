package com.project.ychengspmall.service.user.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.project.ychengspmall.common.service.exception.YchengException;
import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.model.dto.h5.UserLoginDto;
import com.project.ychengspmall.model.dto.h5.UserRegisterDto;
import com.project.ychengspmall.model.entity.user.UserInfo;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.h5.UserInfoVo;
import com.project.ychengspmall.service.user.mapper.UserInfoMapper;
import com.project.ychengspmall.service.user.service.UserInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public void register(UserRegisterDto userRegisterDto) {

        // 获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //校验参数
        if (StrUtil.isEmpty(username) ||
                StrUtil.isEmpty(password) ||
                StrUtil.isEmpty(nickName) ||
                StrUtil.isEmpty(code)) {
            throw new YchengException(ResultCodeEnum.DATA_ERROR);
        }

        //校验校验验证码
        String codeValueRedis = stringRedisTemplate.opsForValue().get("phone:code:" + username);
        if (!code.equals(codeValueRedis)) {
            throw new YchengException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if (null != userInfo) {
            throw new YchengException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //保存用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtil.md5Hex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar(
                "http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(userInfo);

        // 删除Redis中的数据
        stringRedisTemplate.delete("phone:code:" + username);

    }

    @Override
    public Object login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if (StrUtil.isEmpty(username) ||
                StrUtil.isEmpty(password)) {
            throw new YchengException(ResultCodeEnum.DATA_ERROR);
        }

        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if (null == userInfo) {
            throw new YchengException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        String md5InputPassword = DigestUtil.md5Hex(password.getBytes());
        if (!md5InputPassword.equals(userInfo.getPassword())) {
            throw new YchengException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验是否被禁用
        if (userInfo.getStatus() == 0) {
            throw new YchengException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //生成token并返回
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        stringRedisTemplate.opsForValue()
                .set("user:spmall:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
       /* log.info(token);
        String userinfojson = stringRedisTemplate.opsForValue().get("user:spmall:" + token);
        log.info(userinfojson);
        if (StrUtil.isBlank(userinfojson)) {
            throw new YchengException(ResultCodeEnum.LOGIN_AUTH);
        }
        UserInfo userInfo = JSON.parseObject(userinfojson, UserInfo.class);
        log.info(userInfo.toString());
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;*/

        //直接从AuthContextUtil中获取对应的userInfo
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;
    }

    @Override
    public void logout(String token) {
        stringRedisTemplate.opsForValue().getAndDelete("user:spmall:" + token);
    }
}
