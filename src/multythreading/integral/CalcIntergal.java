package multythreading.integral;

public class CalcIntergal {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        double res = Integral.singleThread(Math::sin, 0, Math.PI/2);
        long end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end-start + " ms");

        start = System.currentTimeMillis();
        res = Integral.multyThread(Math::sin, 0, Math.PI/2);
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end-start + " ms");

        start = System.currentTimeMillis();
        res = Integral.forkJoin(Math::sin, 0, Math.PI/2);
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end-start + " ms");

        start = System.currentTimeMillis();
        res = Integral.multyStream(Math::sin, 0, Math.PI/2);
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end-start + " ms");

    }
}
