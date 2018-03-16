import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {

    public static int cutoff = 1000;

    public static int threadCount = 0;

    public static void sort(int[] array, int from, int to) {
        int size = to - from;
        if (size < cutoff) Arrays.sort(array, from, to);
        else {
            int mid = from + (to - from) / 2;
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid); // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, mid, to); // TODO implement me
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        int x1start = 0;
                        int x2start = 0;
                        for (int i = 0; i < xs1.length + xs2.length; i++) {
                            if (x1start >= xs1.length) result[i] = xs2[x2start++];
                            else if (x2start >= xs2.length) result[i] = xs1[x1start++];
                            else if (xs1[x1start] < xs2[x2start]) result[i] = xs1[x1start++];
                            else result[i] = xs2[x2start++];
                        }
                        // TODO implement me
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
                // TODO implement me
                if (throwable != null) {
                    parsort.completeExceptionally(throwable);
                } else {
                    for (int i = 0; i < result.length; i++) {
                        array[i] = result[i];
                    }
                }

//               throwable.getMessage();

            });
            System.out.println("complete");
            parsort.join();
        }
    }


    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    for (int i = 0; i < to - from; i++) {
                        result[i] = array[i + from];
                    }
                    sort(result, 0, result.length);
                    return result;
                    // TODO implement me
                }
        );
    }
}

