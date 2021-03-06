package fci.swe2.onlineshopapi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWT {

    enum UserType{
        CUSTOMER,
        STORE_OWNER,
        ADMIN
    }

    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    private static long duration = 60 * 60 * 1000; // 1 hour

    public static String createToken(Account account){
        UserType type = null;
        if(account instanceof Admin){
            type = UserType.ADMIN;
        }else if(account instanceof Customer){
            type = UserType.CUSTOMER;
        }else if(account instanceof StoreOwner){
            type = UserType.STORE_OWNER;
        }

        SignatureAlgorithm algo = SignatureAlgorithm.HS256;
        long timenow = System.currentTimeMillis();
        Date now = new Date(timenow);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, algo.getJcaName());

        JwtBuilder jwt = Jwts.builder()
            .claim("user_id", account.getUserID())
            .claim("user_type", type)
            .setIssuedAt(now)
            .signWith(algo,signingKey);

        if(duration > 0) {
            long expiration = timenow + duration;
            Date exp = new Date(expiration);
            jwt.setExpiration(exp);
        }

        return jwt.compact();
    }

    public static Claims decodeJWT(String jwt) {
        Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
