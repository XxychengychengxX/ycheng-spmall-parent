package com.project.ychengspmall.service.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.project.ychengspmall.model.entity.product.Category;
import com.project.ychengspmall.service.product.mapper.CategoryMapper;
import com.project.ychengspmall.service.product.service.CategoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findOneCategory() {
        return categoryMapper.findOneCategory();
    }

    @Override
    public List<Category> findCategoryTree() {
        /*List<Category> categoryList = categoryMapper.findAll();
        //全部一级分类
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0).collect(
                Collectors.toList());
        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            //遍历一级分类
            oneCategoryList.forEach(oneCategory -> {
                //获取二级分类
                List<Category> twoCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue()).collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);
                //获取三级分类
                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue()).collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;*/

        // 从Redis缓存中查询所有的一级分类数据
        String categoryListJsonString = stringRedisTemplate.opsForValue().get("category:one");
        if (!StrUtil.isEmpty(categoryListJsonString)) {
            List<Category> categoryList = JSON.parseArray(categoryListJsonString, Category.class);
            log.info("从Redis缓存中查询到了所有的一级分类数据");
            return categoryList;
        }

        List<Category> categoryList = categoryMapper.findOneCategory();
        log.info("从数据库中查询到了所有的一级分类数据");
        stringRedisTemplate.opsForValue().set("category:one", JSON.toJSONString(categoryList), 12, TimeUnit.HOURS);
        return categoryList;
    }

}
