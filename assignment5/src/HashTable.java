import java.util.Random;

public class HashTable {
    private static final Random random = new Random();

    private int[] mockHashTable;
    private int tablesize;

    public HashTable(int tablesize) {
        this.tablesize = tablesize;
        mockHashTable = new int[tablesize];
        for (int i = 0; i < tablesize; i++) {
            mockHashTable[i] = 0;
        }
    }

    public int hash(Integer key) {
        return (key.hashCode() & 0x7fffffff) % tablesize;
    }

    public void insert(int key) {
        int hashKey = hash(key);
        mockHashTable[hashKey]++;
    }

    public int getValue(int key) {
        return mockHashTable[key];
    }

    public static void main(String[] args) {
//        int M = 100;
        HashTable birthTable;

        for (int M = 100; M < 10000; M += 100) {
            int birthResult = 0;
            for (int forAverage = 0; forAverage < 100; forAverage++) {
                birthTable = new HashTable(M);
                boolean flag = true;
                int count = 0;
                while (flag) {
                    birthTable.insert(random.nextInt(M));
                    count++;
                    for (int birthday : birthTable.mockHashTable) {
                        if (birthday == 2) {
                            flag = false;
                            break;
                        }
                    }
                }
                birthResult += count;
            }
            System.out.println(birthResult / 100);
        }


//        int N = 100;
        HashTable couponTable;
        for (int N = 100; N < 10000; N += 100) {
            int couponResult = 0;
            for (int forAverage = 0; forAverage < 100; forAverage++) {
                couponTable = new HashTable(N);
                boolean flag = true;
                int count = 0;
                while (flag) {
                    flag = false;
                    couponTable.insert(random.nextInt(N));
                    count++;
                    for (int birthday : couponTable.mockHashTable) {
                        if (birthday == 0) {
                            flag = true;
                            break;
                        }
                    }
                }
                couponResult += count;
            }
            System.out.println(couponResult / 100);
        }
    }
}
