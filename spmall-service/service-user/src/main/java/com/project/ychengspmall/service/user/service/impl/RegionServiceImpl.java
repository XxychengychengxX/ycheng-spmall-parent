package com.project.ychengspmall.service.user.service.impl;

import com.project.ychengspmall.model.entity.base.Region;
import com.project.ychengspmall.service.user.mapper.RegionMapper;
import com.project.ychengspmall.service.user.service.RegionService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 @author XxychengychengxX
 @date 2024/2/6
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "region")
public class RegionServiceImpl implements RegionService {

    @Resource
    RegionMapper regionMapper;

    @Override
    @Cacheable(value = "region:code",key = "#parentCode")
    public List<Region> findByParentCode(Integer parentCode) {
//        List<Region> all = regionMapper.findAll();
        List<Region> byParentCode = regionMapper.findByParentCode(parentCode);
        return byParentCode;
    }
}
