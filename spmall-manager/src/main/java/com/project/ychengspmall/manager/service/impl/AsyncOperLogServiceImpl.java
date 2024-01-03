package com.project.ychengspmall.manager.service.impl;

import com.project.ychengspmall.common.log.service.AsyncOperLogService;
import com.project.ychengspmall.manager.mapper.SysOperLogMapper;
import com.project.ychengspmall.model.entity.system.SysOperLog;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {
    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Async      // 异步执行保存日志操作
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }

}
