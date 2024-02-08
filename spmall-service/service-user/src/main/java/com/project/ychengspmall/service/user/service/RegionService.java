package com.project.ychengspmall.service.user.service;

import com.project.ychengspmall.model.entity.base.Region;

import java.util.List;

/**
 @author XxychengychengxX
 @date 2024/2/6
 */
public interface RegionService {
    List<Region> findByParentCode(Integer parentCode);
}
