package com.project.ychengspmall.manager.service.impl;

import com.project.ychengspmall.manager.mapper.ProductUnitMapper;
import com.project.ychengspmall.manager.service.ProductUnitService;
import com.project.ychengspmall.model.entity.base.ProductUnit;
import jakarta.annotation.Resource;

import java.util.List;

public class ProductUnitServiceImpl implements ProductUnitService {
    // com.atguigu.spzx.manager.service.impl
    @Resource
    private ProductUnitMapper productUnitMapper ;

    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll() ;
    }
}
