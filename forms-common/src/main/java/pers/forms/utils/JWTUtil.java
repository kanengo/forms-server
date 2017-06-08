package pers.forms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 * Created by Administrator on 2017/6/8.
 */
public class JWTUtil {

    public static String encode(String key){
        return encode(key, null, null, 0);
    }

    public static String encode(String key, Object obj, long maxAge){
        return encode(key, null, obj, maxAge);
    }

    public static String encode(String key, String issue,  Object obj, long maxAge) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);

            JWTCreator.Builder builder = JWT.create();
            if (issue != null)
                builder.withIssuer(issue);
            if (obj != null){
                ObjectMapper mapper = new ObjectMapper();
                String sub = mapper.writeValueAsString(obj);
                builder.withSubject(sub);
            }
            if (maxAge >= 0){
                long expireAt = System.currentTimeMillis() + maxAge;
                builder.withExpiresAt(new Date(expireAt));
            }

            String token = builder.sign(algorithm);
            return token;
        }catch (UnsupportedEncodingException |JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    public static  VerifyResult verify(String key, String token){
        return verify(key, null, token);
    }

    public static VerifyResult verify(String key, String issue, String token){
        VerifyResult result = new VerifyResult(false, null);
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm)
                            .withIssuer(issue)
                            .build();
            DecodedJWT jwt = verifier.verify(token);
            result.setSuccess(true);
            result.setJwt(jwt);
            return result;
        }catch (UnsupportedEncodingException | JWTVerificationException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

    public static <T> T decode(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
        }catch (JWTDecodeException e){
            e.printStackTrace();
        }
        return null;
    }

    public static class VerifyResult {
        private boolean success = false;

        private String message;

        private DecodedJWT jwt;

        public DecodedJWT getJwt() {
            return jwt;
        }

        public void setJwt(DecodedJWT jwt) {
            this.jwt = jwt;
        }

        public VerifyResult(boolean success, String message){
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
