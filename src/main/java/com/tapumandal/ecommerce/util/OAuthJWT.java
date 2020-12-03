package com.tapumandal.ecommerce.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by TapuMandal on 12/4/2020.
 * For any query ask online.tapu@gmail.com
 */

@Service
public class OAuthJWT {
    //HMAC
    Algorithm algorithmHS = Algorithm.HMAC256("secret");

    public void verifyToken(String token){
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        RSAPublicKey publicKey = null;//Get the key instance
        RSAPrivateKey privateKey = null;//Get the key instance
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }
    }


    final JwkStore jwkStore = new JwkStore("{JWKS_FILE_HOST}");
    final RSAPrivateKey privateKey = //Get the key instance
    final String privateKeyId = //Create an Id for the above key

        RSAKeyProvider keyProvider = new RSAKeyProvider() {
        @Override
        public RSAPublicKey getPublicKeyById(String kid) {
            //Received 'kid' value might be null if it wasn't defined in the Token's header
            RSAPublicKey publicKey = jwkStore.get(kid);
            return (RSAPublicKey) publicKey;
        }

        @Override
        public RSAPrivateKey getPrivateKey() {
            return privateKey;
        }

        @Override
        public String getPrivateKeyId() {
            return privateKeyId;
        }
    };

}
