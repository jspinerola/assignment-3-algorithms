public class Main {
    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        System.out.println("************************ CHAT GPT O3 MINI HIGH ************************");
        System.out.println("Algorithm 1 Results");
        System.out.printf("%-10s|%-10s|%-32s\n", "Answer", "Duration", "Num. of Recursions/Iterations");
        for (int n = 2; n <= 10; n += 2) {
            Algoritms algos = new Algoritms();
            int[] res = algos.runAlgorithm(0, prices, n);
            System.out.printf("%-10d %-10d %-32d\n", res[0], res[1], res[2]);
        }
        System.out.println();
        System.out.println("Algorithm 2 Results");
        System.out.printf("%-10s|%-10s|%-32s\n", "Answer", "Duration", "Num. of Recursions/Iterations");
        for (int n = 2; n <= 10; n += 2) {
            Algoritms algos = new Algoritms();
            int[] res = algos.runAlgorithm(1, prices, n);
            System.out.printf("%-10d %-10d %-32d\n", res[0], res[1], res[2]);
        }
        System.out.println();
        System.out.println("Algorithm 3 Results");
        System.out.printf("%-10s|%-10s|%-32s\n", "Answer", "Duration", "Num. of Recursions/Iterations");
        for (int n = 2; n <= 10; n += 2) {
            Algoritms algos = new Algoritms();
            int[] res = algos.runAlgorithm(2, prices, n);
            System.out.printf("%-10d %-10d %-32d\n", res[0], res[1], res[2]);
        }

        System.out.println("************************ CLAUDE 3.7 SONNET ************************");
        System.out.println("Algorithm 1 Results");
        System.out.printf("%-10s|%-10s|%-32s\n", "Answer", "Duration", "Num. of Recursions/Iterations");
        for (int n = 2; n <= 10; n += 2) {
            Algoritms algos = new Algoritms();
            int[] res = algos.runAlgorithm(3, prices, n);
            System.out.printf("%-10d %-10d %-32d\n", res[0], res[1], res[2]);
        }
        System.out.println();
        System.out.println("Algorithm 2 Results");
        System.out.printf("%-10s|%-10s|%-32s\n", "Answer", "Duration", "Num. of Recursions/Iterations");
        for (int n = 2; n <= 10; n += 2) {
            Algoritms algos = new Algoritms();
            int[] res = algos.runAlgorithm(4, prices, n);
            System.out.printf("%-10d %-10d %-32d\n", res[0], res[1], res[2]);
        }
        System.out.println();
        System.out.println("Algorithm 3 Results");
        System.out.printf("%-10s|%-10s|%-32s\n", "Answer", "Duration", "Num. of Recursions/Iterations");
        for (int n = 2; n <= 10; n += 2) {
            Algoritms algos = new Algoritms();
            int[] res = algos.runAlgorithm(5, prices, n);
            System.out.printf("%-10d %-10d %-32d\n", res[0], res[1], res[2]);
        }
        System.out.println();
    }
}


