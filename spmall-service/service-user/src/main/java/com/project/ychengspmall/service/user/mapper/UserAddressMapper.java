package com.project.ychengspmall.service.user.mapper;

import com.project.ychengspmall.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@Mapper
public interface UserAddressMapper {

    List<UserAddress> findByUserId(Long userId);

    UserAddress getById(Long id);
}
