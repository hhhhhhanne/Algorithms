import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.util.Arrays.*;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) ParSort.cutoff = Integer.parseInt(args[0]);
        Random random = new Random(0L);
        int[] array = new int[2000000];
        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
//        int[] aux = Arrays.copyOf(array,array.length);
//        Arrays.sort(aux);
//        System.out.println(Arrays.toString(aux));

        //ParSort.sort(array, 0, array.length);
        //for (int i : array) System.out.println(i);
//        System.out.println(Arrays.toString(array));
        //if (array[0] == 11) System.out.println("Success!");

//        for (int j = 10000; j <= 2500000; j += 10000) {
            ParSort.cutoff = 500000;
            double totalTime = 0;
            double t2=0;
            for (int k = 0; k < 1; k++) {
                double startTime = System.currentTimeMillis();
                ParSort.sort(array.clone(), 0, array.length);
                double endTime = System.currentTimeMillis();
                double runTime = endTime - startTime;
                totalTime +=runTime;
                double s2=System.currentTimeMillis();
                Arrays.parallelSort(array.clone(),0,array.length);
                double e2=System.currentTimeMillis();
                double r2=e2-s2;
                t2+=r2;
            }
            double averageTime = totalTime/10;
            double averSys = t2/10;
            System.out.println("cutoff:"+ ParSort.cutoff+" time:"+averageTime + "Sys:" +averSys);
//        }
    }
}
