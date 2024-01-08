package demo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtCreation {
    public String jwtToken(String channel){
        Algorithm algorithm = Algorithm.HMAC256("secret-key");
        String token;
        token = JWT.create()
                .withIssuer("auth0")
                .withClaim("channel", channel)
                .sign(algorithm);
        return token;
    }
}
