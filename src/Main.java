import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        for (int n = 2; n <= 10; n += 2) {
            Algoritms algos = new Algoritms();
            System.out.println(Arrays.toString(algos.runAlgorithm(0, prices, n)));
        }

    }
}