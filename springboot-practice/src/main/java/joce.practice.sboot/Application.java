package joce.practice.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author jocelyn hu
 * @Date 2019/07/25
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
