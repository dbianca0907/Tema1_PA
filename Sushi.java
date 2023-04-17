import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Sushi {

	static int n, m, x;
	static int[] prices;
	static int[][] grades;

	Sushi(){}

	static int task1() {
		int[] sum_grades = new int[m + 1];

		// Realizez suma notelor pentru fiecare platou, de la fiecare persoana
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				sum_grades[i] += grades[j][i];
			}
		}

		int[][] dp = new int[m + 1][n * x + 1];
		//aplic algoritmul rucsac
		for (int i = 1; i <= m; ++i) {
			for (int price = 0; price <= n * x; ++price) {
				if (price - prices[i] >= 0) {
					// de explicat
					dp[i][price] = Math.max(dp[i - 1][price],
							dp[i - 1][price - prices[i]] + sum_grades[i]);
				} else {
					dp[i][price] = dp[i - 1][price];
				}
			}
		}
		return dp[m][n * x];
	}

	static int task2() {
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
			for (int price = 0; price <= n * x; ++price) {
				if (price - duplicat_prices[i] >= 0) {
					dp[i][price] = Math.max(dp[i - 1][price],
							dp[i - 1][price - duplicat_prices[i]] + duplicat_sum[i]);
				} else {
					dp[i][price] = dp[i - 1][price];
				}
			}
		}
		return dp[2 * m][n * x];
	}

	static int task3() {
		int[] sum_grades = new int[m + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				sum_grades[i] += grades[j][i];
			}
		}

		int[][][] dp = new int[2 * m + 1][n * x + 1][n + 1];
		int[] duplicat_prices = new int[2 * m + 1];
		int[] duplicat_sum = new int[2 * m + 1];

		System.arraycopy(prices, 1, duplicat_prices, 1, m);
		System.arraycopy(prices, 1, duplicat_prices, m + 1, m);

		System.arraycopy(sum_grades, 1, duplicat_sum, 1, m);
		System.arraycopy(sum_grades, 1, duplicat_sum, m + 1, m);

		for (int i = 1; i <= 2 * m; ++i) {
			for (int price = 0; price <= n * x; ++price) {
				for (int k = 1; k <= n; k++) {
					if (price - duplicat_prices[i] >= 0) {
						dp[i][price][k] = Math.max(dp[i - 1][price][k],
								dp[i - 1][price - duplicat_prices[i]][k - 1] + duplicat_sum[i]);
					} else {
						dp[i][price][k] = dp[i - 1][price][k];
					}
				}
			}
		}
		return dp[2 * m][n * x][n];
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("sushi.in"));

			final int task = sc.nextInt(); // task number

			n = sc.nextInt(); // number of friends
			m = sc.nextInt(); // number of sushi types
			x = sc.nextInt(); // how much each of you is willing to spend

			prices = new int[m + 1]; // prices of each sushi type
			// the grades you and your friends gave to each sushi type
			grades = new int[n + 1][m + 1];

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

			int ans;
			switch (task) {
				case 1:
					ans = Sushi.task1();
					break;
				case 2:
					ans = Sushi.task2();
					break;
				case 3:
					ans = Sushi.task3();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("sushi.out");
				fw.write(Integer.toString(ans) + '\n');
				fw.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
