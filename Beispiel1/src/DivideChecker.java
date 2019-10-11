import java.util.List;

public class DivideChecker implements Runnable {
    private List<Integer> sublist;
    private int divider;

    public DivideChecker(List<Integer> sublist, int divider) {
        this.sublist = sublist;
        this.divider = divider;
    }

    @Override
    public void run() {
        sublist.forEach((n) -> {
            if (isDivider(n.intValue(), divider)) {
                System.out.println(n);
            }
        });
    }

    public static boolean isDivider(int number, int divider) {
        return number % divider == 0;
    }
}
