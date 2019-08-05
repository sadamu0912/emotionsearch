package com.xjxspace.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import com.xjxspace.service.ISmsService;
import com.xjxspace.service.ServiceResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements ISmsService, InitializingBean {

    @Value("${aliyun.sms.accessKeyID}")
    private String accessKeyID;

    @Value("${aliyun.sms.accessKeySecret}")
    private String secertKey;

    @Value("${aliyun.sms.template.code}")
    private String templateCode;

    private IAcsClient acsClient;

    private final static String SMS_CODE_CONTENT_PREFIX = "SMS::CODE::CONTENT";

    private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final Random random = new Random();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ServiceResult<String> sendSms(String telephone) {
        String gapKey = "SMS::CODE::INTERVAL::" + telephone;
        String result = redisTemplate.opsForValue().get(gapKey);
        if (result != null) {
            return new ServiceResult<String>(false, "请求次数太频繁");
        }

        String code = generateRandomSmsCode();
        code="123456";
        String templateParam = String.format("{\"code\": \"%s\"}", code);

        // 组装请求对象
//        CommonRequest request = new CommonRequest();
//
//        // 使用post提交
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", telephone);
//        request.putQueryParameter("SignName", "智能表情网");
//        request.putQueryParameter("TemplateCode", "SMS_171859070");
//        request.putQueryParameter("TemplateParam", templateParam);

        boolean success = false;
        try {
//            CommonResponse response = acsClient.getCommonResponse(request);
//            System.out.println(JSON.toJSONString(response));
//            System.out.println(response.getData());
//            if (200==response.getHttpStatus()) {
//                success = true;
//            } else {
//                // TODO log this question
//            }
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (success) {
            redisTemplate.opsForValue().set(gapKey, code, 60, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(SMS_CODE_CONTENT_PREFIX + telephone, code, 10, TimeUnit.MINUTES);
            return ServiceResult.of(code);
        } else {
            return new ServiceResult<String>(false, "服务忙，请稍后重试");
        }
    }

    @Override
    public String getSmsCode(String telephone) {
        return this.redisTemplate.opsForValue().get(SMS_CODE_CONTENT_PREFIX + telephone);
    }

    @Override
    public void remove(String telephone) {
        this.redisTemplate.delete(SMS_CODE_CONTENT_PREFIX + telephone);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyID, secertKey);

       // String product = "Dysmsapi";
      //  String domain = "dysmsapi.aliyuncs.com";

      //  DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        this.acsClient = new DefaultAcsClient(profile);

    }

    /**
     * 6位验证码生成器
     * @return
     */
    private static String generateRandomSmsCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(10);
            sb.append(NUMS[index]);
        }
        return sb.toString();
    }
}
