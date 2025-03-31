import java.util.Arrays;

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
        } else if (algorithmPicker == 2) {
            int[] cuts = new int[100];
            result = rodCuttingBottomUp(prices, n, cuts);
        }
        else if (algorithmPicker == 3) {
            result = cutRodClaude(prices, n);
        }
        else if (algorithmPicker == 4) {
            result = cutRodTopDownClaude(prices, n);
        }
        else if (algorithmPicker == 5) {
            int[] cuts = new int[100];
            result = rodCuttingBottomUp(prices, n, cuts);
        }
        return result;
    }

    /** ALGO 1 - ChatGPT **/
    public int[] rodCut(int[] prices, int n) {
        recursiveCalls++;
        long startTime = System.nanoTime();
        // Base case: no rod left means zero revenue
        if (n == 0) {
            int duration = (int)( System.nanoTime() - startTime);
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

    /** ALGO 2 - ChatGPT **/
    // Method that initializes the memoization array and starts the recursion.
    public static int[] rodCutMemorization(int[] price, int n) {
        recursiveCalls++;
        long startTime = System.nanoTime();
        // Create a memo array to store solutions for subproblems.
        // Initialize all entries to -1 indicating not computed.
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }

        return new int[]{rodCutUtil(price, n, memo), (int)( System.nanoTime() - startTime), recursiveCalls};
    }

    // Recursive helper function that returns the maximum revenue for a rod of length n.
    private static int rodCutUtil(int[] price, int n, int[] memo) {
        recursiveCalls++;
        // Base case: no length, no revenue.
        if (n == 0) {
            return 0;
        }
        // Return memoized result if available.
        if (memo[n] != -1) {
            return memo[n];
        }

        int maxRevenue = Integer.MIN_VALUE;

        // Try every possible first cut and choose the one that gives maximum revenue.
        for (int i = 1; i <= n; i++) {
            // price[i-1] is the price for a rod of length i.
            int revenue = price[i - 1] + rodCutUtil(price, n - i, memo);
            maxRevenue = Math.max(maxRevenue, revenue);
        }

        // Store the result in memo array.
        memo[n] = maxRevenue;
        return maxRevenue;
    }

    /** ALGO 3 - ChatGPT **/
    /**
     * Solves the rod cutting problem using bottom-up dynamic programming.
     * It computes the maximum revenue for a rod of length n using the provided
     * prices array (where prices[i] is the price of a rod of length i+1).
     *
     * @param prices The array of prices (index 0 represents length 1, index 1 represents length 2, etc.)
     * @param n      The total length of the rod.
     * @param cuts   An array to record the optimal first cut for each rod length.
     * @return An array containing maximum revenue for each rod length from 0 to n.
     */
    public static int[] rodCuttingBottomUp(int[] prices, int n, int[] cuts) {
        long startTime = System.nanoTime();

        // revenue[j] will store the maximum revenue for a rod of length j.
        int[] revenue = new int[n + 1];
        revenue[0] = 0; // Base case: a rod of length 0 has revenue 0.

        // Compute revenue[j] for j = 1 to n.
        for (int j = 1; j <= n; j++) {
            int maxRevenue = Integer.MIN_VALUE;
            // Try every possible first cut.
            for (int i = 1; i <= j; i++) {
                recursiveCalls++;
                // prices[i - 1] is the price for a piece of length i.
                int currentRevenue = prices[i - 1] + revenue[j - i];
                if (currentRevenue > maxRevenue) {
                    maxRevenue = currentRevenue;
                    // Record the best first cut for rod length j.
                    cuts[j] = i;
                }
            }
            revenue[j] = maxRevenue;
        }
        return new int[]{revenue[revenue.length - 1], (int)( System.nanoTime() - startTime), recursiveCalls};
    }

    /**
     * Prints the optimal solution by displaying the cuts that lead to maximum revenue.
     *
     * @param n    The total length of the rod.
     * @param cuts The array that stores the optimal first cut for each rod length.
     */
    public static void printSolution(int n, int[] cuts) {
        System.out.print("The optimal cuts are: ");
        while (n > 0) {
            System.out.print(cuts[n] + " ");
            n = n - cuts[n];
        }
        System.out.println();
    }

    /** ALGO 1 - Claude **/
    /**
     * Solves the Rod Cutting Problem using a pure recursive approach without memoization.
     * The Rod Cutting Problem: Given a rod of length n and an array of prices for rod pieces of length 1 to n,
     * determine the maximum value obtainable by cutting up the rod and selling the pieces.
     *
     * @param prices Array where prices[i] is the price of a rod of length i+1
     * @param n Length of the rod
     * @return Maximum profit that can be obtained
     */
    public static int[] cutRodClaude(int[] prices, int n) {
        recursiveCalls++;
        long startTime = System.nanoTime();

        // Base case: If rod length is 0, no profit can be made
        if (n == 0) {
            return new int[]{0, (int)( System.nanoTime() - startTime), recursiveCalls};
        }

        // Initialize maximum value as negative infinity
        int maxValue = Integer.MIN_VALUE;

        // Consider all possible cuts and find the one that gives maximum value
        for (int i = 0; i < n; i++) {
            // Cut the rod at position i, which gives a piece of length (i+1)
            // and a remaining piece of length (n-(i+1))
            // recursively find the best way to cut the remaining piece
            maxValue = Math.max(maxValue, prices[i] + cutRodClaude(prices, n - (i + 1))[0]);
        }

        return new int[]{maxValue, (int)( System.nanoTime() - startTime), recursiveCalls};
    }
    /** ALGO 2 - Claude **/
    /**
     * Solves the Rod Cutting Problem using top-down dynamic programming with memoization.
     *
     * @param prices Array containing the price of each rod length
     * @param n Length of the rod to be cut
     * @return Maximum value obtainable by cutting the rod
     */
    public static int[] cutRodTopDownClaude(int[] prices, int n) {
        recursiveCalls++;
        long startTime = System.nanoTime();
        // Create memoization array initialized to -1
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return new int[]{cutRodMemoizedClaude(prices, n, memo), (int)( System.nanoTime() - startTime), recursiveCalls};
    }

    /**
     * Helper method that implements the memoized recursive solution
     *
     * @param prices Array containing the price of each rod length
     * @param n Length of the rod to be cut
     * @param memo Memoization array to store already computed results
     * @return Maximum value obtainable by cutting the rod of length n
     */
    private static int cutRodMemoizedClaude(int[] prices, int n, int[] memo) {
        recursiveCalls++;

        // If the result is already calculated, return it
        if (memo[n] >= 0) {
            return memo[n];
        }

        int maxValue;

        // Base case: if rod length is 0, no value can be obtained
        if (n == 0) {
            maxValue = 0;
        } else {
            // Initialize maximum value as negative infinity
            maxValue = Integer.MIN_VALUE;

            // Try all possible rod cuts and find the maximum value
            for (int i = 1; i <= n; i++) {
                // prices[i-1] represents the price of rod of length i
                // We consider cutting a piece of length i and recursively solve for the remaining length n-i
                maxValue = Math.max(maxValue, prices[i-1] + cutRodMemoizedClaude(prices, n - i, memo));
            }
        }

        // Store the result in the memoization array
        memo[n] = maxValue;
        return maxValue;
    }

    /** ALGO 3 - Claude **/
    /**
     * Solves the rod cutting problem using bottom-up dynamic programming.
     *
     * @param prices Array of prices for each rod length, where prices[i] is the price for a rod of length i+1
     * @param n Length of the rod to be cut
     * @return An array containing the maximum revenue and the cuts to make
     */
    public static int[] solveRodCuttingClaude(int[] prices, int n) {
        long startTime = System.nanoTime();

        // dp[i] stores the maximum revenue for a rod of length i
        int[] dp = new int[n + 1];

        // cuts[i] stores the first cut to make for a rod of length i
        int[] cuts = new int[n + 1];

        // Base case: rod of length 0 has 0 value
        dp[0] = 0;

        // Fill the dp array bottom-up
        for (int i = 1; i <= n; i++) {
            int maxRevenue = Integer.MIN_VALUE;

            // Try all possible cuts for current length
            for (int j = 1; j <= i; j++) {
                recursiveCalls++;
                // If cutting at position j gives better revenue, update maxRevenue and cuts
                if (prices[j - 1] + dp[i - j] > maxRevenue) {
                    maxRevenue = prices[j - 1] + dp[i - j];
                    cuts[i] = j; // Store the optimal cut length
                }
            }

            dp[i] = maxRevenue;
        }

        return new int[] {dp[n], (int)( System.nanoTime() - startTime), recursiveCalls};
    }

    /**
     * Prints the optimal solution including all cuts to make.
     *
     * @param cuts Array that stores the first cut to make for each rod length
     * @param n Length of the rod
     */
    public static void printSolutionClaude(int[] cuts, int n) {
        System.out.println("Optimal cuts for rod of length " + n + ":");

        while (n > 0) {
            System.out.print(cuts[n] + " ");
            n = n - cuts[n];
        }
        System.out.println();
    }
}
