package com.project.ychengspmall.service.user.service.impl;

import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.model.entity.user.UserAddress;
import com.project.ychengspmall.service.user.mapper.UserAddressMapper;
import com.project.ychengspmall.service.user.service.UserAddressService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@Service
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findByUserId(userId);
    }

    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.getById(id);

    }


}