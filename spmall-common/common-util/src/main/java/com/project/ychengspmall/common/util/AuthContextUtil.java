package com.project.ychengspmall.common.util;

import com.project.ychengspmall.model.entity.system.SysUser;
import com.project.ychengspmall.model.entity.user.UserInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthContextUtil {

    // 创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<UserInfo> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    // 定义存储数据的静态方法
    public static void set(SysUser sysUser) {
        THREAD_LOCAL.set(sysUser);
    }

    // 定义获取数据的方法
    public static SysUser get() {
        return THREAD_LOCAL.get();
    }

    // 删除数据的方法
    public static void remove() {
        THREAD_LOCAL.remove();
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return USER_INFO_THREAD_LOCAL.get();
    }

    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        USER_INFO_THREAD_LOCAL.remove();
    }
}
