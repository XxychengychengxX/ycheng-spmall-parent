package com.project.ychengspmall.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.project.ychengspmall.common.service.exception.YchengException;
import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.manager.helper.MenuHelper;
import com.project.ychengspmall.manager.mapper.SysMenuMapper;
import com.project.ychengspmall.manager.mapper.SysRoleMenuMapper;
import com.project.ychengspmall.manager.service.SysMenuService;
import com.project.ychengspmall.model.entity.system.SysMenu;
import com.project.ychengspmall.model.entity.system.SysUser;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.system.SysMenuVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> sysMenuList = sysMenuMapper.selectAll();
        if (CollectionUtil.isEmpty(sysMenuList)) return null;
        return MenuHelper.buildTree(sysMenuList);

    }

    @Override
    @Transactional
    public void save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        // 新添加一个菜单，那么此时就需要将该菜单所对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu);
    }

    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {

        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.selectById(sysMenu.getParentId());

        if (parentMenu != null) {

            // 将该id的菜单设置为半开
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());

            // 递归调用
            updateSysRoleMenuIsHalf(parentMenu);

        }

    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        int count = sysMenuMapper.countByParentId(id);  // 先查询是否存在子菜单，如果存在不允许进行删除
        if (count > 0) {
            throw new YchengException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);// 不存在子菜单直接删除
    }

    @Override
    public List<SysMenuVo> findUserMenuList() {

        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();          // 获取当前登录用户的id

        //直接根据userId查出当前角色的所有树
        List<SysMenu> sysMenuList = sysMenuMapper.selectListByUserId(userId);

        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtil.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }


}
