package joce.practice.batch.init;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/07/18
 */
@Data
@NoArgsConstructor
public class Person {
    private String lastName;
    private String firstName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
