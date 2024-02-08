package com.project.ychengspmall.service.user.controller;

import cn.hutool.core.collection.CollUtil;
import com.project.ychengspmall.model.entity.base.Region;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.service.user.service.RegionService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 @author XxychengychengxX
 @date 2024/2/6
 */
@RestController
@Slf4j
@RequestMapping("/api/user/region")
public class RegionController {

    @Resource
    RegionService regionService;
    @GetMapping("/findByParentCode/{parentCode}")
    public Result findByParentCode(@PathVariable("parentCode") Integer parentCode) {
        List<Region> byParentCode = regionService.findByParentCode(parentCode);
        if (CollUtil.isNotEmpty(byParentCode)){
            return Result.build(byParentCode, ResultCodeEnum.SUCCESS);
        }
        return Result.build(null, ResultCodeEnum.DATA_ERROR);
    }

}
