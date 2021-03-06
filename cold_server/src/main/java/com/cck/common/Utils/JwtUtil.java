package com.cck.common.Utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AAS on 2018/3/26.
 */
public class JwtUtil {

    static final String SECRET = "#$this&IS&a%$secret$";

    // 保存6小时
    private static final long cacheTime =  6 * 60 * 60 * 1000;

    public static String generateToken(String userId) {


        long curTime = System.currentTimeMillis();
        JSONObject accessTokenValue = (JSONObject) FileUtils.getValue("jwtToken", userId);

        String token = "";
        if (accessTokenValue == null || curTime - accessTokenValue.getLong("begin_time") >= cacheTime) {
            HashMap<String, Object> map = new HashMap<>();
            //you can put any data in the map
            map.put("userId", userId);
            String jwt = Jwts.builder()
                    .setClaims(map)
                    .setExpiration(new Date(System.currentTimeMillis() + 8*60*60*1000)) // 8小时 （ 100 hour 360_000_000L）
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            token = "Bearer " + jwt;//jwt前面一般都会加Bearer


            /**
             * 把token写进文件
             */
            JSONObject jsontemp = new JSONObject();
            JSONObject jsonAccess = new JSONObject();
            jsontemp.clear();
            jsontemp.put("token", token);
            jsontemp.put("begin_time", curTime);
            jsonAccess.put(userId, jsontemp);
            FileUtils.write2File(jsonAccess, "jwtToken");
        }else {

            return accessTokenValue.getString("token");

        }


        return  token;
    }

    public static void validateToken(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        }catch (Exception e){
            throw new IllegalStateException("Invalid Token. "+e.getMessage());
        }
    }

}
