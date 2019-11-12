package joce.practice.concurrency.st.entity;

import lombok.Data;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
@Data
public class SecurityParameters {
    private String enduser_id;

    public SecurityParameters() {
        this.enduser_id = "192.168.1.1";
    }
}
