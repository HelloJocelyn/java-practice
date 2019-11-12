package joce.practice.concurrency;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/08/23
 */
public class TimerTaskTest {
    public static void main(String[] args) {

    }

    public void setTask(){
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.submit(()->{
//            System.out.println("test");
//        });
        Runnable task=()->{System.out.println("test");};
        executorService.scheduleAtFixedRate(task,0,10, TimeUnit.MILLISECONDS);

    }
}
