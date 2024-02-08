package com.project.ychengspmall.service.user.mapper;

import com.project.ychengspmall.model.entity.base.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 @author XxychengychengxX
 @date 2024/2/6
 */
@Mapper
public interface RegionMapper {
    List<Region> findAll();

    List<Region> findByParentCode(@Param("parentCode") Integer parentCode);

    List<Region> findByCode(@Param("codeList") List<String> provinceCode);
}
