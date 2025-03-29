public class Algoritms {
    public static int recursiveCalls = 0;

    public Algoritms(){
        recursiveCalls = 0;
    }

    public int[] runAlgorithm(int algorithmPicker, int[] prices, int n){
        int[] result  = {1, 2, 3};
        recursiveCalls = 0;
        if (algorithmPicker == 0) {
            result = rodCut(prices, n);
        } else if (algorithmPicker == 1) {
            result = rodCutMemorization(prices, n);
        }
        return result;
    }

    // Algorithm 1
    public int[] rodCut(int[] prices, int n) {
        recursiveCalls++;
        long startTime = System.nanoTime();
        // Base case: no rod left means zero revenue
        if (n == 0) {
            int duration = (int)(startTime - System.nanoTime());
            return new int[]{0, duration, recursiveCalls};
        }
        int maxRevenue = Integer.MIN_VALUE;

        // Try cutting the rod at every possible length i (from 1 to n)
        for (int i = 1; i <= n; i++) {
            // prices array is 0-indexed, so we use prices[i - 1]
            int currentRevenue = prices[i - 1] + rodCut(prices, n - i)[0];
            maxRevenue = Math.max(maxRevenue, currentRevenue);
        }
        int duration = (int)( System.nanoTime() - startTime);
        return new int[]{maxRevenue, duration, recursiveCalls};
    }

    public static int[] rodCutMemorization(int[] prices, int n) {

        return new int[]{123871024, 421,2};
    }
}
