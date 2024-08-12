package com.codefinity;

import java.util.concurrent.ForkJoinPool;

public class RunnableTask {
    public static void main(String[] args) {
        long[] array = new long[1000]; // Example array
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        TaskForkJoin task = new TaskForkJoin(array, 0, array.length);
        long result = pool.invoke(task); // Start the task and get the result

        System.out.println("Sum: " + result); // Print the result
    }
}
