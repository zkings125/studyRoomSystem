package com.h.ssRoom.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.h.ssRoom.model.Admins;

public class JWTHelper {
    public  static  String createJWTToken(Admins admins){
        String token = JWT.create().withAudience(admins.getAdminID()).sign(Algorithm.HMAC256(admins.getAdminPwd()));
        return token;
    }
}
