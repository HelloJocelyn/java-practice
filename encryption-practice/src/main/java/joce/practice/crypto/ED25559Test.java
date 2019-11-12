package joce.practice.crypto;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;

import javax.servlet.http.Cookie;
import java.util.HashMap;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/08/26
 */
public class ED25559Test {
    public static void main(String[] args) {
//        final JWEHeader jweHeader = new JWEHeader(JWEAlgorithm.ECDH_ES_A256KW, EncryptionMethod.A256GCM);
//
//        JWTClaimsSet claims = new JWTClaimsSet();
//        claims.setCustomClaim("email","sanjay@example.com");
//
//        final Payload payload = new Payload(claims.toJSONObject());
//
//        final JWEObject jweObject = new JWEObject(jweHeader, payload);
        final HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("a","b");

    }
}
