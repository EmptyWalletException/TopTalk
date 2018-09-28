package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * @Description: JSON web token 工具类;
 * @Author: MasterCV
 * @Date: Created in  2018/9/28 17:07
 */
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String key;//加密密钥;
    private long ttl;//有效时长;

    /**
     * 生成jwt信息;
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);//创建时间用于下面生成永不重复的token信息;
        //开始创建token,设置一些基本的信息
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)//设置签发时间;
                .signWith(SignatureAlgorithm.HS256, key)//设置加密方式和加密密钥;
                .claim("roles", roles);//设置额外的信息, 可以重复使用.claim方法存入键值对;
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));//设置截止日期,即当前时间+有效时长;
        }
        return builder.compact();

    }

    /**
     * 解析jwt信息;
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return Jwts.parser()
                .setSigningKey(key)//调用密钥用于反编译;
                .parseClaimsJws(jwtStr)//解析传入的jwt字符串;
                .getBody();//获取jwt信息体,jwt是由头部header,载荷playload,签证signature三个部分组成;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
