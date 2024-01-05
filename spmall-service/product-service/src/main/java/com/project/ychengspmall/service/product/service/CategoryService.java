package com.project.ychengspmall.service.product.service;

import com.project.ychengspmall.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findOneCategory();

    List<Category> findCategoryTree();

}
