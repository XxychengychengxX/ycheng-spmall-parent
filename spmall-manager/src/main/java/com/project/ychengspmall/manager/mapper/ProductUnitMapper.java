package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> findAll();

}
