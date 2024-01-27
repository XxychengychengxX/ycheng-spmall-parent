package com.project.ychengspmall.service.user.controller;

import com.project.ychengspmall.model.entity.user.UserAddress;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.service.user.service.UserAddressService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 用户地址接口
 @author XxychengychengxX
 @Date 2024/1/21
 */
@RestController
@RequestMapping(value = "/api/user/userAddress")
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    @GetMapping("auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList() {
        List<UserAddress> list = userAddressService.findUserAddressList();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 获取地址信息
     * @param id
     * @return
     */
    @GetMapping("getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        return userAddressService.getById(id);
    }
}
