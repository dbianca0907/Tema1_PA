import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;

public class TEST {
    static final int  mod = 1000000007;

    static int n, m, x;
    static int[] prices;
    static int[][] grades;

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        final int task = sc.nextInt(); // task number

        n = sc.nextInt(); // number of friends
        m = sc.nextInt(); // number of sushi types
        x = sc.nextInt(); // how much each of you is willing to spend

        prices = new int[m]; // prices of each sushi type
        grades = new int[n][m]; // the grades you and your friends gave to each sushi type

        // price of each sushi
        for (int i = 0; i < m; ++i) {
            prices[i] = sc.nextInt();
        }

        // each friends rankings of sushi types
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                grades[i][j] = sc.nextInt();
            }
        }

        int[] sum_grades = new int[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum_grades[i] += grades[j][i];
            }
        }

        int[][] dp = new int[m + 1][n * x + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = prices[i]; j <= n * x; j++) {
                if (j >= prices[i]) { // nu am mai comandat un platou de tip i pana atunci
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - prices[i]] + sum_grades[i]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prices[i]] + sum_grades[i]);
                }
                System.out.println(dp[i][j]);

            }
        }
        System.out.println(dp[n][n * x]);
    }
}
/*
1
4 2 6
6 10
7 9
5 10
6 10
9 8
 */