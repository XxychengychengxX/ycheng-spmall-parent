package com.project.ychengspmall.manager.test;

import cn.hutool.crypto.digest.DigestUtil;
import com.project.ychengspmall.manager.properties.UserAuthProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertiesTest {

    @Autowired
    UserAuthProperties userAuthProperties;

    @Test
    void printProperties() {
        System.out.println(userAuthProperties.getNoConfirmUrls());
    }

    @Test
    void getMd5Hex(){
        String s = DigestUtil.md5Hex("admin");
        String s1 = DigestUtil.md5Hex("111111");
        System.out.println(s);
        System.out.println(s1);
    }
}
