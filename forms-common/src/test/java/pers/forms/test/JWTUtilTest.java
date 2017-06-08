package pers.forms.test;

import pers.forms.utils.JWTUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/8.
 */
public class JWTUtilTest {
    public static void main(String[] args) throws InterruptedException{
        String token = JWTUtil.encode("ljn", 5000);
        System.out.println(token);
        TimeUnit.SECONDS.sleep(2);
        JWTUtil.VerifyResult result =  JWTUtil.verify(token);
        if (result.isSuccess()) {
            System.out.println(result.getJwt().getSubject());
        }else{
            System.out.println(result.getMessage());
        }
    }
}
