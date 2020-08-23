package multythreading.executors;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        long sum = 0;
        for(long i = 1; i <= 100_000_000L; i++){
            //double si = Math.sin(5);
            sum += i;
        }
        return sum;
    }
}
