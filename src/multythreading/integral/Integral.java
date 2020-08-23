package multythreading.integral;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.DoubleFunction;

public class Integral {
    public static final int STEPS = 25_000_000;



    //y = f(x)
    static double singleThread(DoubleFunction<Double> f, double start, double end, int steps){
        double h = (end-start)/steps;
        double sum = 0;

        for(int i = 0; i < steps; i++){
            double middle = start + h*i + h/2;
            double y = f.apply(middle);
            sum += h * y;
        }
        return sum;
    }
    static double singleThread(DoubleFunction<Double> f, double start, double end){
        return singleThread(f, start, end, STEPS);
    }

    static double multyThread(DoubleFunction<Double> f, double start, double end, int steps){
        int processors = Runtime.getRuntime().availableProcessors();
        //processors = 2;
        double delta = (end - start)/processors;
        ExecutorService pool = Executors.newFixedThreadPool(processors);
        List<Future<Double>> futureTaskList = new ArrayList<>();
        for(int i = 0; i < processors; i++){
            int countDelta = i;
            Future<Double> futureTask = pool.submit(() -> singleThread(f, start + delta*countDelta, start + delta*(countDelta+1), steps/processors));
            futureTaskList.add(futureTask);
        }
        pool.shutdown();

        List<Double> res = new ArrayList<>();

        for (Future<Double> future : futureTaskList){
            try {
                res.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);

        return res.stream().reduce(Double::sum).orElse(0d);
    }
    static double multyThread(DoubleFunction<Double> f, double start, double end) {
        return multyThread(f, start, end, STEPS);
    }

    static double forkJoin(DoubleFunction<Double> f, double start, double end, int steps){
        int processors = Runtime.getRuntime().availableProcessors();
        //processors = 2;
        double delta = (end - start)/processors;
        ExecutorService pool = Executors.newFixedThreadPool(processors);
        List<Future<Double>> futureTaskList = new ArrayList<>();
        for(int i = 0; i < processors; i++){
            int countDelta = i;
            Future<Double> futureTask = pool.submit(() -> singleThread(f, start + delta*countDelta, start + delta*(countDelta+1), steps/processors));
            futureTaskList.add(futureTask);
        }
        pool.shutdown();

        List<Double> res = new ArrayList<>();

        for (Future<Double> future : futureTaskList){
            try {
                res.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);

        return res.stream().reduce(Double::sum).orElse(0d);
    }
    static double forkJoin(DoubleFunction<Double> f, double start, double end){
        return forkJoin(f, start, end, STEPS);
    }

    static double multyStream(DoubleFunction<Double> f, double start, double end, int steps){
       class Pair{
           double start;
           double end;

           public Pair(double start, double end) {
               this.start = start;
               this.end = end;
           }
       }
       int tasks = Runtime.getRuntime().availableProcessors();
       double delta = (end - start) / tasks;
       List<Pair> pairs = new ArrayList<>();
       for(int i = 0; i < tasks; i++){
           pairs.add(new Pair(start + i * delta, start + (i + 1) * delta));
       }
        //Used  ForkJoinPool.commonPool()
       return pairs.parallelStream()
               .mapToDouble(pair -> singleThread(f , pair.start, pair.end, steps/tasks))
               .sum();
    }

    static double multyStream(DoubleFunction<Double> f, double start, double end){
        return multyStream(f, start, end, STEPS);
    }





}
