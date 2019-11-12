package joce.practice.concurrency.st.entity;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
@Data
public class TokenRefresh {
    private String ttl;
    private Date expire_at;
    private String token;

    public TokenRefresh() {
        this.ttl = "10d";
//        Calendar instance = Calendar.getInstance();
//        int dayOfYear = instance.get(Calendar.DAY_OF_YEAR);
//        instance.set(Calendar.DAY_OF_YEAR, dayOfYear + 10);
//        this.expire_at = instance.getTime();
    }
}
