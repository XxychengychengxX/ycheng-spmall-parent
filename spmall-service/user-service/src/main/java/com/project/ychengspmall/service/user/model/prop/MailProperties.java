package com.project.ychengspmall.service.user.model.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author admin
 */
@Data
@ConfigurationProperties(prefix = "sms-user")
public class MailProperties {
    String appCode;
    String appKey;
    String appSecret;
}
