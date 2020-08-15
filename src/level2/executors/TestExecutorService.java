package level2.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestExecutorService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService pool = Executors.newCachedThreadPool();
        int procesors = Runtime.getRuntime().availableProcessors();
        System.out.println(procesors);
        ExecutorService pool = Executors.newWorkStealingPool();
        long start = System.currentTimeMillis();
        List<Future<Long>> futureList = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            Future<Long> future = pool.submit(new CallableTask());
            futureList.add(future);
        }

        //Thread.sleep(61*1000);
        //pool.shutdownNow();

        Future<Long> future11 = pool.submit(new CallableTask());
        futureList.add(future11);
        Future<Long> future12 = pool.submit(new CallableTask());
        futureList.add(future12);

        //futureList.get(5).cancel(true);

        List<Long> results = new ArrayList<>();
        for(Future<Long> f : futureList){
            long result = f.get();
            results.add(result);
        }

        long end = System.currentTimeMillis();
        System.out.println(results);

        System.out.println(end-start + "ms");
        pool.shutdown();

    }
}
