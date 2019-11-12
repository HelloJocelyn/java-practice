package joce.practice.concurrency.st.entity;

import lombok.Data;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
@Data
public class Grant {
    private String type;
    private String backend;
    private String username;
    private String password;
    private String value;

    public Grant(String type){
        if ("user::password".equalsIgnoreCase(type)) {
            this.type="user::password";
            this.backend="jid";
        }
        if ("token::refresh".equalsIgnoreCase(type)) {
            this.type=type;

        }

    }
}
