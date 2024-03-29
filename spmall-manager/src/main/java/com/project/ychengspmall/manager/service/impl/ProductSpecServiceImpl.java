package com.project.ychengspmall.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.manager.mapper.ProductSpecMapper;
import com.project.ychengspmall.manager.service.ProductSpecService;
import com.project.ychengspmall.model.entity.product.ProductSpec;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductSpecServiceImpl implements ProductSpecService {

    @Resource
    ProductSpecMapper productSpecMapper;

    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }

    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page , limit) ;
        List<ProductSpec> productSpecList = productSpecMapper.findByPage() ;
        return new PageInfo<>(productSpecList);

    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec) ;

    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);

    }

    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);

    }
}
