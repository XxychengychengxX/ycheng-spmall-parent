package com.project.ychengspmall.service.product.controller;

import com.project.ychengspmall.model.entity.product.Category;
import com.project.ychengspmall.model.entity.product.ProductSku;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.h5.IndexVo;
import com.project.ychengspmall.service.product.service.CategoryService;
import com.project.ychengspmall.service.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页接口
 * @author admin
 */
@RestController
@RequestMapping(value = "/api/product/index")
@SuppressWarnings({"unchecked"})
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    /**
     * 获取首页数据
     * @return 返回首页数据
     */
    @GetMapping
    public Result<IndexVo> findData() {
        List<Category> categoryList = categoryService.findOneCategory();
        List<ProductSku> productSkuList = productService.findProductSkuBySale();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
