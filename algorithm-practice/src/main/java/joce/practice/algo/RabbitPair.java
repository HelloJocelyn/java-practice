package joce.practice.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/11/08
 */
public class RabbitPair {
    private int childrenNumber = 0;
    private List<RabbitPair> children;
    private int age = 0;

    public RabbitPair(int age) {
        this.age = age;
        this.children = new ArrayList<>();
    }

    private int bearChild() {
        RabbitPair rabbitPair = new RabbitPair(1);
        children.add(rabbitPair);
//         childrenNumber+=2;
        return 2;
    }

    private int grow() {
        this.age++;
        int count = 0;
        for (RabbitPair child : children) {
            count+= child.grow();
        }
        if (this.age >= 3) {
            count += bearChild();
        }
        childrenNumber +=count;
        return count;
        // how many born this year
    }

    public static void main(String[] args) {
        RabbitPair root = new RabbitPair(0);
        for (int i = 1; i <= 20; i++) {
            root.grow();

        }
        System.out.println(root.childrenNumber);

    }
}
