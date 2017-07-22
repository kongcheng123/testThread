import java.util.concurrent.TimeUnit;

public class safeKill {
    /**
     * 安全的终止线程
     */
    public static void main(String[] args) throws Exception{
        Runner runner=new Runner();
        Thread thread=new Thread(runner,"one");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        Runner two=new Runner();
        Thread thread2=new Thread(runner,"one");
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
        System.out.println(thread.isInterrupted());
    }

    private static class Runner implements Runnable{
        private Long l;

        private volatile boolean on = true;

        @Override
        public void run() {
            while( on && Thread.currentThread().isInterrupted() ){
                l++;
            }
            System.out.println("c = "+l);
        }

        public void cancel(){
            on=false;
        }
    }
}
