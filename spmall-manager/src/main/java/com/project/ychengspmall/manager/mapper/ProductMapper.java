package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.dto.product.ProductDto;
import com.project.ychengspmall.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findByPage(ProductDto productDto);

    void save(Product product);

    Product selectById(Long id);

    void updateById(Product product);

    void deleteById(Long id);
}
