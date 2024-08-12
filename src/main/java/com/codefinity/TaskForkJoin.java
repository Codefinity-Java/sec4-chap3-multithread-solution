package com.codefinity;

import java.util.concurrent.RecursiveTask;

public class TaskForkJoin extends RecursiveTask<Long> {
    private static final int THRESHOLD = 200; // Threshold for task splitting
    private final long[] array;
    private final int start;
    private final int end;

    public TaskForkJoin(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Perform the task directly if the size of the subarray is below the threshold
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Split the task into sub-tasks
            int mid = (start + end) / 2;
            TaskForkJoin leftTask = new TaskForkJoin(array, start, mid);
            TaskForkJoin rightTask = new TaskForkJoin(array, mid, end);
            leftTask.fork(); // Start the task for the left part
            rightTask.fork(); // Start the task for the right part
            return leftTask.join() + rightTask.join(); // Combine the results
        }
    }

}
