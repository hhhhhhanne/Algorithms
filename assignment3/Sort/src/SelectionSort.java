
public class SelectionSort<X extends Comparable<X>> implements Sort<X> {

    @Override
    public void sort(X[] xs, int from, int to) {
        int n = xs.length;
//        if (to >= xs.length) {
//            System.out.println("note:to<n");
//            return;
//        }
        for (; from <to; from++) {
            int min = from;
            for (int j = from + 1; j < to; j++) {
                if (Helper.less(xs[j], xs[min])) {
                    min = j;
                }
            }
            Helper.swap(xs, from, min);
        }
    }
}
