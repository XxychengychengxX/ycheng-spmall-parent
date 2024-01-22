package com.project.ychengspmall.service.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.project.ychengspmall.common.service.exception.YchengException;
import com.project.ychengspmall.common.util.HttpUtils;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.service.user.model.prop.MailProperties;
import com.project.ychengspmall.service.user.service.SmsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource
    HttpUtils httpUtils;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    MailProperties mailProperties;

    @Override
    public void sendValidateCode(String phone) {
        String code = stringRedisTemplate.opsForValue().get("phone:code:" + phone);
        if (StrUtil.hasLetter(code)) {
            return;
        }

        String validateCode = RandomStringUtils.randomNumeric(4);      // 生成验证码
        stringRedisTemplate.opsForValue()
                .set("phone:code:" + phone, validateCode, 5, TimeUnit.MINUTES);
        sendSms(phone, validateCode);
    }

    public void sendSms(String phone, String validateCode) {

        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = mailProperties.getAppCode();
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("content", "code:" + validateCode);
        bodys.put("template_id", "CST_ptdie100");
        bodys.put("phone_number", phone);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = httpUtils.doPost(host, path, method, headers, querys,
                    bodys);
            log.info(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new YchengException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

}
