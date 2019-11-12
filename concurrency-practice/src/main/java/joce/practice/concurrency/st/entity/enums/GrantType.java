package joce.practice.concurrency.st.entity.enums;

import lombok.Data;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/08
 */

public enum GrantType {
    UserPassword("user::password"), TokenRefresh("token::refresh");
    private String value;


    GrantType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
