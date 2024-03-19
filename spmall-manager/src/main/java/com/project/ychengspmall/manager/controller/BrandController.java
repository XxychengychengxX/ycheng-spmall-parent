package com.project.ychengspmall.manager.controller;


import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.manager.service.BrandService;
import com.project.ychengspmall.model.entity.product.Brand;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌控制器
 * @author admin
 */
@RestController
@RequestMapping(value = "/admin/product/brand")
public class BrandController {

    @Resource
    private BrandService brandService;


    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        Integer save = brandService.save(brand);
        if (save == 0) {
            return Result.build(null, ResultCodeEnum.ERROR_BUSINESS);
        }else {
            return Result.build(null, ResultCodeEnum.SUCCESS);
        }
    }

    @PutMapping("/updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}