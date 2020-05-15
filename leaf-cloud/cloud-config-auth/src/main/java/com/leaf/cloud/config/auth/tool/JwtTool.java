package com.leaf.cloud.config.auth.tool;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leaf.cloud.config.auth.base.Leaf;
import com.leaf.cloud.config.auth.base.ResultLeaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class JwtTool {

    private static final Logger logger = LoggerFactory.getLogger(JwtTool.class);

    private String secret;

    private Algorithm algorithm;

    private JWTVerifier jwtVerifier;

    public String createUserToken(String userName){
//        Jwts.builder().addClaims()

        Date now = Calendar.getInstance().getTime();
        now.setTime(now.getTime()+2*60*60*1000);
//        now.setTime(now.getTime()+20*1000);
        try{
            String token = JWT.create()
                    .withSubject(userName)
                    .withClaim("userName",userName)
                    .withClaim("userId",userName+"-id")
                    .withExpiresAt(now)
                    .sign(this.algorithm);
            return  token;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }

    public Leaf verifyUserToken(String token){
        return this.verifyUserToken(token,null);
    }

    /**
     *  @throws AlgorithmMismatchException     if the algorithm stated in the token's header it's not equal to the one defined in the {@link JWTVerifier}.
     *  @throws SignatureVerificationException if the signature is invalid.
     *  @throws TokenExpiredException          if the token has expired.
     *  @throws InvalidClaimException          if a claim contained a different value than the expected one.
     * @param token
     * @param userName
     * @return
     */


    public Leaf verifyUserToken(String token,String userName){
       try {


           DecodedJWT decodedJWT = this.jwtVerifier.verify(token);
           String userNameSub = decodedJWT.getSubject();
           String userId = decodedJWT.getClaim("userId").asString();
//           decodedJWT.getClaims()
          return ResultLeaf.success(userNameSub);
       }catch (AlgorithmMismatchException ae){

           logger.error( "token 验证失败",ae);
           return ResultLeaf.fail(ae);

       }catch (SignatureVerificationException se){

           logger.error( "token 验证失败",se);
           return ResultLeaf.fail(se);

       }catch (TokenExpiredException te){
           //if the token has expired
//           return "token 验证失败："+te.getMessage();
           logger.error( "token 验证失败",te);
           return ResultLeaf.fail(te);

       }catch (InvalidClaimException ie){

           logger.error( "token 验证失败",ie);
           return ResultLeaf.fail(ie);
//           System.out.println(ie.getMessage());
//           return "token 验证失败："+ie.getMessage();
       }

    }

    public JwtTool() {
        this.secret = StringUtils.isEmpty(secret)? "leaf-secret":secret;
        this.algorithm = Algorithm.HMAC256(this.secret);
        this.jwtVerifier = JWT.require(this.algorithm).build();
    }

}
