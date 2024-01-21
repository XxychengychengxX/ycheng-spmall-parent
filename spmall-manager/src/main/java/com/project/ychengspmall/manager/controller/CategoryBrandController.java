package com.project.ychengspmall.manager.controller;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.manager.service.CategoryBrandService;
import com.project.ychengspmall.model.dto.product.CategoryBrandDto;
import com.project.ychengspmall.model.entity.product.Brand;
import com.project.ychengspmall.model.entity.product.CategoryBrand;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/admin/product/category-brand")
public class CategoryBrandController {
    @Resource
    CategoryBrandService categoryBrandService;
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList =   categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList , ResultCodeEnum.SUCCESS) ;
    }



    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, CategoryBrandDto CategoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, CategoryBrandDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @PutMapping("/updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
