package joce.practice.concurrency.st.entity.enums;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/08
 */
public enum TokenType {
    EXCHANGE("token::refresh"), REFRESH("token::exchange");

    private String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
