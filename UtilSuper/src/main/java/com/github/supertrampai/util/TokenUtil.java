package com.github.supertrampai.util;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TokenUtil {

	private static final Logger LOG = LoggerFactory.getLogger(TokenUtil.class);
    public static String createToken(Integer uid) {
        if (uid != null) {
            SecurityUtil security = new SecurityUtil();
            String token = security.des_encrypt(uid + "|" + DateUtil.DateToStr(Calendar.getInstance().getTime(), "yyyyMMddHHmm"));
//            RedisUtils.saveOrUpdate(token,uid);
            return token;
        }
        return null;
    }

    public static String getUidFromToken(String token){
        if(token != null){
            SecurityUtil security = new SecurityUtil();
            String origin = security.des_decrypt(token);
            LOG.info(token+"---"+origin);
            if(origin != null){
                String[] origins = origin.split("\\|");
                if(origins.length==2){
                    return origins[0];
                }
            }
        }
        return null;
    }

}
