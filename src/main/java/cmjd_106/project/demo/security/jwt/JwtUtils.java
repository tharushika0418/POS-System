package cmjd_106.project.demo.security.jwt;

import java.security.Key;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtUtils {
    @Value("${app.secret}")
    private String jwtSecret;


    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    //Generate JWt Token for the userdetails
    public String generateJwtToken(Authentication authentication){
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new java.util.Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key(),SignatureAlgorithm.HS256)
                .compact();

    }
//Validate JwtToekn
    public boolean validateJwtToken(String token){
       try{
        Jwts.parserBuilder().setSigningKey(key()).build().parse(token);

        return true;

       }catch(Exception e){
           e.printStackTrace();
           return false;
       } 
    } 


    //get username from jwt token
    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }


    
    
}
