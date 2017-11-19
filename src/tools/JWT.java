package tools;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;  

public class JWT {
	
	private static String key = "random_secret_key";
	private static String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
	
	public static String createJWT(String id, long ttlMillis) {
	 
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Key);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	    
	    return builder.compact();
	}
	
	public static boolean checkJWT(String jwt, int id) {
		if(jwt==null || jwt.isEmpty())
			return false;
		
		try {
		    Claims claims = Jwts.parser()         
		       .setSigningKey(DatatypeConverter.parseBase64Binary(base64Key))
		       .parseClaimsJws(jwt).getBody();
		    
		    if(claims.getId() != null && claims.getExpiration() != null)
		    {
			    Date now = new Date(System.currentTimeMillis());
			    
			    if(claims.getExpiration().after(now) || Integer.parseInt(claims.getId()) != id)
			    	return false;
			    else
			    	return true;
		    }
		}
		catch(Exception e) {System.out.println(e); return false;}
		return false;
	}
}
