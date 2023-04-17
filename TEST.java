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

        prices = new int[m + 1]; // prices of each sushi type
        grades = new int[n + 1][m + 1]; // the grades you and your friends gave to each sushi type

        // price of each sushi
        for (int i = 1; i <= m; ++i) {
            prices[i] = sc.nextInt();
        }

        // each friends rankings of sushi types
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                grades[i][j] = sc.nextInt();
            }
        }

        int[] sum_grades = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum_grades[i] += grades[j][i];
            }
        }

        int[][] dp = new int[2 * m + 1][n * x + 1];
        int[] duplicat_prices = new int[2 * m + 1];
        int[] duplicat_sum = new int[2 * m + 1];
        
        System.arraycopy(prices, 1, duplicat_prices, 1, m);
        System.arraycopy(prices, 1, duplicat_prices, m + 1, m);
        
        System.arraycopy(sum_grades, 1, duplicat_sum, 1, m);
        System.arraycopy(sum_grades, 1, duplicat_sum, m + 1, m);
        
        for (int i = 1; i <= 2 * m; ++i) {
            for (int grade = 0; grade <= n * x; ++grade) {
                if (grade - duplicat_prices[i] >= 0) {
                    dp[i][grade] = Math.max(dp[i - 1][grade], dp[i - 1][grade - duplicat_prices[i]] + duplicat_sum[i]);
                } else {
                    dp[i][grade] = dp[i-1][grade];
                }
            }
        }
        System.out.println(dp[2 * m][n * x]);
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