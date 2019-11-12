package joce.practice.concurrency.st.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
@Data
public class Artifacts {

    @SerializedName("token::refresh")
    private TokenRefresh tokenRefresh;
    @SerializedName("token::exchange")
    private TokenExchange tokenExchange;

    public Artifacts(String tokenType){
        if ("token::refresh".equalsIgnoreCase(tokenType)) {
            this.tokenRefresh=new TokenRefresh();
        }
        if ("token::exchange".equalsIgnoreCase(tokenType)) {
            this.tokenExchange=new TokenExchange();
        }

    }
    public Artifacts(String tokenType,String audience){
        if ("token::refresh".equalsIgnoreCase(tokenType)) {
            this.tokenRefresh=new TokenRefresh();
        }
        if ("token::exchange".equalsIgnoreCase(tokenType)) {
            this.tokenExchange=new TokenExchange(audience);
        }

    }
}
