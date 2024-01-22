package com.project.ychengspmall.service.product.controller;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.dto.h5.ProductSkuDto;
import com.project.ychengspmall.model.entity.product.ProductSku;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.h5.ProductItemVo;
import com.project.ychengspmall.service.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品列表
 * @author admin
 */
@RestController
@RequestMapping(value = "/api/product")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 商品列表搜索分页
     * @param page 当前页数
     * @param limit 每页记录数
     * @param productSkuDto 搜索条件对象
     * @return 商品列表
     */
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable Integer page, @PathVariable Integer limit,
                                                   ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品详情
     * @param skuId 商品skuId
     * @return 商品详情
     */
    @GetMapping("item/{skuId}")
    public Result<ProductItemVo> item(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.build(productItemVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据skuId获取商品sku
     * @param skuId 商品skuId
     * @return 商品sku具体信息
     */
    @GetMapping("getBySkuId/{skuId}")
    public ProductSku getBySkuId(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        ProductSku productSku = productService.getBySkuId(skuId);
        return productSku;
    }
}
