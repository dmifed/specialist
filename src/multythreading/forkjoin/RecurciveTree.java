package multythreading.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecurciveTree {
    static Random random = new Random();
    static long totalWeight = 0;

    public static void createRandomTree(TreeNode node, int level){
        node.left = new TreeNode();
        node.right = new TreeNode();
        node.weight = random.nextInt(100);
        totalWeight += node.weight;
        level--;

        if(level == 0){
            node.left.weight = random.nextInt(100);
            node.right.weight = random.nextInt(100);
            totalWeight += node.left.weight;
            totalWeight += node.right.weight;

            return;
        }

        createRandomTree(node.left, level);
        createRandomTree(node.right, level);
    }

    public static long weightTree(TreeNode root){
        return root.weight +
                (root.left != null ? weightTree(root.left) : 0) +
                (root.right != null ? weightTree(root.right) : 0);
    }

    public static long weightTreeMulty(TreeNode root){

        class WeightCounter extends RecursiveTask<Long>{
            TreeNode treeNode;
            int level;

            public WeightCounter(TreeNode treeNode, int level) {
                this.treeNode = treeNode;
                this.level = level;
            }

            @Override
            protected Long compute() {
                final int newLevel = level-1;
                if(level <= 0) return weightTree(treeNode);

                long sum = treeNode.weight;

                WeightCounter weightCounter1 = null;
                WeightCounter weightCounter2 = null;

                if(treeNode.left != null){
                    weightCounter1 = new WeightCounter(treeNode.left, newLevel);
                    weightCounter1.fork();
                }

                if(treeNode.right != null){
                    weightCounter2 = new WeightCounter(treeNode.right, newLevel);
                    weightCounter2.fork();
                }

                if(weightCounter1 != null){
                    sum += weightCounter1.join();
                }

                if(weightCounter2 != null){
                    sum += weightCounter2.join();
                }

                return sum;


            }
        }
        int precessors = Runtime.getRuntime().availableProcessors();

        return ForkJoinPool
                .commonPool()
                .invoke(new WeightCounter(root, precessors ));
    }

    public static void main(String[] args) {
        int treeLevel = 24;

        TreeNode root = new TreeNode();
        long start = System.currentTimeMillis();
        createRandomTree(root, treeLevel);
        long end = System.currentTimeMillis();
        System.out.printf("Creating tree %d ms\n", end-start);

        //profile
        weightTree(root);
        weightTree(root);

        start = System.currentTimeMillis();
        long wSingle = weightTree(root);
        end = System.currentTimeMillis();
        System.out.printf("Calc with one thread %d ms\n", end-start);

        //profile
        weightTreeMulty(root);
        weightTreeMulty(root);

        start = System.currentTimeMillis();
        long wMulty = weightTreeMulty(root);
        end = System.currentTimeMillis();
        System.out.printf("Calc with forkJoinPool %d ms\n", end-start);



    }
}
