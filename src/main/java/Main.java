import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * Created by xy on 2017/7/11.
 */
public class Main {

    private volatile int a=1;

    public static void main(String[] args) {
        /*MyThread myThread=new MyThread();
        myThread.start();*/
        Thread thread=new Thread(new MyRunnable(),"firstThread");
        thread.start();
        System.out.println(thread.getName());
    }

    @Test
    public void test(){
        Thread thread=new Thread(){
            @Override
            public void run() {
                System.out.println("匿名函数实现");
            }
        };
        thread.start();
    }

    @Test
    public void testRunnable(){
        Runnable runnable=new Runnable(){
            public void run() {
                System.out.println("匿名实现Runnable");
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }

    @Test
    public void getName(){
        System.out.println(Thread.currentThread().getName());
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    System.out.println(someMethod());
                }
            }.start();
        }
    }

    public synchronized int someMethod(){

        int threadSafeInt = 0;

        return ++threadSafeInt;
    }
    public synchronized int someMethod1(){

        int threadSafeInt = 1;

        return ++threadSafeInt;
    }

    @Test
    public void testMXBean(){
        ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos=threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println("["+threadInfo.getThreadId()+"] "+threadInfo.getThreadName());

        }
    }

    @Test
    public void testThrow(){
        throw new NullPointerException("cannot be found");
    }
}
