package com.project.ychengspmall.service.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.model.entity.base.Region;
import com.project.ychengspmall.model.entity.user.UserAddress;
import com.project.ychengspmall.model.entity.user.UserInfo;
import com.project.ychengspmall.service.user.mapper.RegionMapper;
import com.project.ychengspmall.service.user.mapper.UserAddressMapper;
import com.project.ychengspmall.service.user.service.UserAddressService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@Service
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    RegionMapper regionMapper;
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

    @Override
    public void save(UserAddress userAddress) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        Long userId = userInfo.getId();
        userAddress.setUserId(userId);
        //根据code查询region,并且设置最终地址
        String provinceCode = userAddress.getProvinceCode();
        String cityCode = userAddress.getCityCode();
        String districtCode = userAddress.getDistrictCode();

        List<Region> byCode = regionMapper.findByCode(List.of(provinceCode, cityCode, districtCode)).stream()
                .sorted((Comparator.comparingInt(o -> Integer.parseInt(o.getCode())))).toList();

        StringBuilder stringBuilder = new StringBuilder();
        byCode.forEach(region -> stringBuilder.append(region.getName()));
        stringBuilder.append(userAddress.getAddress());
        userAddress.setFullAddress(stringBuilder.toString());

        //设置时间相关
        userAddress.setCreateTime(new Date());
        userAddress.setUpdateTime(new Date());

        userAddressMapper.save(userAddress);


    }

    @Override
    public void deleteById(Long id) {
        userAddressMapper.setDeleteById(id);
    }


}