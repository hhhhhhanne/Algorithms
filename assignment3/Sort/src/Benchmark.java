/*
 * Copyright (c) 2018. Phasmid Software
 */

import java.util.Random;
import java.util.function.Function;

public class Benchmark<T> {
    private static Integer[] temp;

    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public double run(T t, int n) {
        double result = 0;
        for (int i = 0; i < n; i++) {
            double start = System.currentTimeMillis();
            f.apply(t);
            double end = System.currentTimeMillis();
            double take = end - start;
            result += take;
        }
        return result / n;
    }

    private final Function<T, Void> f;


    public static void main(String[] args) {
        Random random = new Random();
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        Integer[] arrayOrdered = new Integer[3200];
        for (int i = 0; i < arrayOrdered.length; i++) {
            arrayOrdered[i] = i;
        }

        Integer[] arrayRandom = new Integer[3200];
        Integer[] arrayReversed = new Integer[3200];
        Integer[] arrayPartiallySorted = arrayOrdered.clone();

        for (int i = 0; i < arrayRandom.length; i++) {
            arrayRandom[i] = Math.abs(random.nextInt(arrayRandom.length));
        }
        for (int i = 0; i < arrayReversed.length; i++) {
            arrayReversed[i] = arrayReversed.length - i - 1;
        }
        for (int i =0; i< arrayPartiallySorted.length;i++){
            if (i%2==0){
//                arrayPartiallySorted[i]=i;//the even positions are sorted while odd are not
            }else {
                arrayPartiallySorted[i]=Math.abs(random.nextInt(arrayPartiallySorted.length));
            }
        }
        System.out.println("this is for warming up");
        for (int j=0;j<10;j++){
            benchmarkSort(arrayOrdered, arrayOrdered.length, "SelectionSort", new SelectionSort<>(), 10);
            benchmarkSort(arrayOrdered, arrayOrdered.length, "InsertionSort", new InsertionSort<>(), 10);
        }
        System.out.println("warm up completely");
        for (int n = 200; n <= 3200; n *= 2) {
            System.out.println("Order");
            benchmarkSort(arrayOrdered, n, "SelectionSort", new SelectionSort<>(), m);
            System.out.println("Random");
            benchmarkSort(arrayRandom, n, "SelectionSort", new SelectionSort<>(), m);
            System.out.println("Reverse");
            benchmarkSort(arrayReversed, n, "SelectionSort", new SelectionSort<>(), m);
            System.out.println("PartiallySorted");
            benchmarkSort(arrayPartiallySorted,n,"SelectionSort",new SelectionSort<>(),m);
        }
        for (int n=200;n<=3200;n*=2){
            System.out.println("Order");
            benchmarkSort(arrayOrdered, n, "InsertionSort", new InsertionSort<>(), m);
            System.out.println("Random");
            benchmarkSort(arrayRandom, n, "InsertionSort", new InsertionSort<>(), m);
            System.out.println("Reverse");
            benchmarkSort(arrayReversed, n, "InsertionSort", new InsertionSort<>(), m);
            System.out.println("PartiallySorted");
            benchmarkSort(arrayPartiallySorted,n,"InsertionSort",new InsertionSort<>(),m);
        }
    }

    private static void benchmarkSort(final Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
        Function<Integer, Void> sortFunction = (x) -> {
            sorter.sort(xs.clone(), 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        System.out.println(name + ": " + x + " millisecs for n=" + n);
    }
}
