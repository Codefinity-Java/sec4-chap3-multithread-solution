import com.codefinity.DonationTask;
import org.junit.jupiter.api.Test;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DonationTaskTest {

    @Test
    public void testCompute_withSmallArray() {
        long[] array = {1, 2, 3, 4, 5}; // Simple array
        DonationTask task = new DonationTask(array, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(task);
        assertEquals(15, result, "Sum of array elements should be 15");
    }

    @Test
    public void testCompute_withLargeArray() {
        long[] array = new long[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        DonationTask task = new DonationTask(array, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(task);
        long expectedSum = 1000L * (1000 + 1) / 2; // Sum of first 1000 natural numbers
        assertEquals(expectedSum, result, "Sum of array elements should match the expected sum");
    }

    @Test
    public void testThresholdSplitting() {
        long[] array = new long[400];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        DonationTask task = new DonationTask(array, 0, array.length);


        ForkJoinPool pool = new ForkJoinPool() {

            <T> T invoke(RecursiveTask<T> task) {
                return super.invoke(task);
            }
        };

        long result = pool.invoke(task);
        long expectedSum = 400L * (400 + 1) / 2;
        assertEquals(expectedSum, result, "Sum of array elements should match the expected sum");
    }
}