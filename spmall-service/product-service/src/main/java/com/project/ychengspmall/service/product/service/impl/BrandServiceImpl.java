package com.project.ychengspmall.service.product.service.impl;

import com.project.ychengspmall.model.entity.product.Brand;
import com.project.ychengspmall.service.product.mapper.BrandMapper;
import com.project.ychengspmall.service.product.service.BrandService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
    @Resource
    BrandMapper brandMapper;

    @Cacheable(value = "brandList", unless = "#result.size() == 0")
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
