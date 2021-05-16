import org.junit.Test;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Tests {

    public static final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
    public static final ScheduledThreadPoolExecutor pool2 = new ScheduledThreadPoolExecutor(1);

    @Test
    public void frameTests(){
        rep();
        rep2();
    }

    /**
     * example of thread pool that uses system.current time to control frames.
     * 25 frames per second
     */
    public void rep(){
        pool.schedule(()->{
            long frameTime = 1000/24;
            int count = 0;
            long startTime = System.currentTimeMillis();
            System.out.println("Test started");
            while (count != 240) {
                long usedTime = System.currentTimeMillis();
                count++;
                if (usedTime - startTime > 1000) {
                    startTime = usedTime;
                    System.out.println(count);
                    count = 0;
                }
                usedTime = System.currentTimeMillis() - usedTime;//gets time passed
                long sleepTime = frameTime - usedTime;
                if (sleepTime > 1) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        },500, TimeUnit.MILLISECONDS);
    }

    public int count = 0;
    long startTime;

    public void rep2(){
        System.out.println("starting rep2");
        startTime = System.currentTimeMillis();
        pool2.scheduleAtFixedRate((()->{
            if(!pool2.getQueue().isEmpty()){System.out.println("Skipping the frame");return;}
            try{
                SwingUtilities.invokeAndWait(()->{
                    count++;
                    long end = System.currentTimeMillis();
                    if(end-startTime>1000){
                        startTime=end;
                        System.out.println(count);
                        count=0;
                    }
                });}
            catch(InvocationTargetException | InterruptedException e) {

                e.printStackTrace();
                System.exit(0);
            }}),500,1000, TimeUnit.MILLISECONDS);

    }
}
