package com.project.ychengspmall.service.product.mapper;

import com.project.ychengspmall.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author XxychengychengxX
 * @date 2024/02/01
 */
@Mapper
public interface CategoryMapper {
    List<Category> findOneCategory();

    List<Category> findAll();



}
