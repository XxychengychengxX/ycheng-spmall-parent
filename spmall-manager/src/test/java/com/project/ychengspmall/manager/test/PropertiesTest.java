package com.project.ychengspmall.manager.test;

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
}
