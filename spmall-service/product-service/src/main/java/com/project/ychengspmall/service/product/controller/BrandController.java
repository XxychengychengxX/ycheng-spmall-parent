package com.project.ychengspmall.service.product.controller;

import com.project.ychengspmall.model.entity.product.Brand;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.service.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌接口
 */
@RestController
@RequestMapping(value = "/api/product/brand")
@SuppressWarnings({"unchecked", "rawtypes"})
public class BrandController {
    @Resource
    private BrandService brandService;

    /**
     * 查找所有品牌
     * @return 所有pin
     */
    @GetMapping("/findAll")
    public Result<List<Brand>> findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
