package com.project.ychengspmall.service.user.mapper;

import com.project.ychengspmall.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    UserInfo getByUsername(String username);

    void save(UserInfo userInfo);


}
