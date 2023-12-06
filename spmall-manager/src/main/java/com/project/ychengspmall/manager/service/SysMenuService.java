package com.project.ychengspmall.manager.service;

import com.project.ychengspmall.model.entity.system.SysMenu;
import com.project.ychengspmall.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);

    List<SysMenuVo> findUserMenuList();

}
