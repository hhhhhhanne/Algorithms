import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class UF {
    private static WQUPC union;
    private static Random random;


    public static int count(int n) {
        int countNum = 0;
        WQUPC union = new WQUPC(n);
        random = new Random();
        while (union.count() > 1) {
            int a = Math.abs(random.nextInt(n));
            int b = Math.abs(random.nextInt(n));
            if (!union.connected(a, b)) {
                union.union(a, b);
            }
            countNum++;
        }
        return countNum;
    }


    public static void main(String[] args) {
        System.out.println("please input an integer to define n");
        BufferedReader brd = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        while (n <= 0) {
            try {
                n = Integer.parseInt(brd.readLine());
            } catch (Exception e) {
                System.out.println("please input an integer");
            }
            if (n <= 0) {
                System.out.println("note:n>0");
            }
        }
        System.out.println("your union is " + n);
        System.out.println("the count of connection is " + count(n));
    }
}
