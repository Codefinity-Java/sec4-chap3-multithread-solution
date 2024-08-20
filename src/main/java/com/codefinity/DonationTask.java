package com.codefinity;

import java.util.concurrent.RecursiveTask;

public class DonationTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 200; // Threshold for task splitting
    private final long[] listDonations;
    private final int start;
    private final int end;

    public DonationTask(long[] listDonations, int start, int end) {
        this.listDonations = listDonations;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Perform the task directly if the size of the subarray is below the threshold
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += listDonations[i];
            }
            return sum;
        } else {
            // Split the task into sub-tasks
            int mid = (start + end) / 2;
            DonationTask leftTask = new DonationTask(listDonations, start, mid);
            DonationTask rightTask = new DonationTask(listDonations, mid, end);
            leftTask.fork(); // Start the task for the left part
            rightTask.fork(); // Start the task for the right part
            return leftTask.join() + rightTask.join(); // Combine the results
        }
    }

}
