package pers.forms.test;

import pers.forms.utils.JWTUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/8.
 */
public class JWTUtilTest {
    private static final String key = "ljn~!@#$%^&*leon./042+";

    private static final String issue = "forms-server";

    public static void main(String[] args) throws InterruptedException{
        String token = JWTUtil.encode(key, issue, "ljn", 5000);
        System.out.println(token);
        TimeUnit.SECONDS.sleep(2);
        JWTUtil.VerifyResult result =  JWTUtil.verify(key, issue, token);
        if (result.isSuccess()) {
            System.out.println(result.getJwt().getSubject());
        }else{
            System.out.println(result.getMessage());
        }
    }
}
