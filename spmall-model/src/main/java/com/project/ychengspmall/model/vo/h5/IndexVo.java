package com.project.ychengspmall.model.vo.h5;


import com.project.ychengspmall.model.entity.product.Category;
import com.project.ychengspmall.model.entity.product.ProductSku;
import lombok.Data;

import java.util.List;

/**
 * @author XxychengychengxX
 */
@Data
public class IndexVo {
    // 一级分类的类别数据
    private List<Category> categoryList ;
    // 畅销商品列表数据
    private List<ProductSku> productSkuList ;

}