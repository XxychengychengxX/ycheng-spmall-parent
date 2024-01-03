package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperLogMapper {
     void insert(SysOperLog sysOperLog);
}
