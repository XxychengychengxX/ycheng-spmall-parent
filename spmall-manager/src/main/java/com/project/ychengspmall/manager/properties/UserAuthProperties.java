package com.project.ychengspmall.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@ConfigurationProperties("spmall.auth")
public class UserAuthProperties {

    List<String> noConfirmUrls;
}
