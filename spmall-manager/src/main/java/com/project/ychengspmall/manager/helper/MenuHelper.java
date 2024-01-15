package com.project.ychengspmall.manager.helper;

import com.project.ychengspmall.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建菜单树的工具类helper
 */
public class MenuHelper {

    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            //判断如果是最顶级顶点（parentID==0)，则寻找他的子节点
            if (sysMenu.getParentId() == 0) {
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        //这里的sysMenu是父级id，只需要获取全部的TreeNodes中parentId为sysMenu的节点
        sysMenu.setChildren(new ArrayList<>());
        for (SysMenu it : treeNodes) {
            if (sysMenu.getId().longValue() == it.getParentId().longValue()) {
                //if (sysMenu.getChildren() == null) {
                //    sysMenu.setChildren(new ArrayList<>());
                //}
                sysMenu.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return sysMenu;
    }
}
