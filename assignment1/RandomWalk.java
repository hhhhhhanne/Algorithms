/*
 * Copyright (c) 2017. Phasmid Software
 */

import java.util.Random;

public class RandomWalk {
    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    public void move(int dx, int dy) {
        // TODO you need to implement this
        x = x + dx;
        y = y + dy;
    }

    private void randomWalk(int n) {
        for (int i = 0; i < n; i++)
            randomMove();
    }

    private void randomMove() {
        // TODO you need to implement this
        int r = random.nextInt(4);
        r = Math.abs(random.nextInt() % 4);
        switch (r){
            case 0:
                move(1,0);
                break;
            case 1:
                move(-1,0);
                break;
            case 2:
                move(0,1);
                break;
            case 3:
                move(0,-1);
                break;
        }
    }

    public double distance() {
        return Math.sqrt(x * x + y * y ); // TODO you need to implement this
    }

    public static void main(String[] args) {
        int[] a = {300,3000,10000,300000,400000,1000000};
        RandomWalk walk = new RandomWalk();
        for(int i = 0 ; i < 6; i++){
            System.out.println();
            walk.x=0;
            walk.y=0;
            for(int j = 0; j < 5; j++){
                walk.x=0;
                walk.y=0;
                walk.randomWalk(a[i]);
                System.out.println(a[i] + " steps: " + walk.distance());
            }
        }
    }
}
