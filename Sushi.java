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
		// TODO solve task 1
		int[] sum_grades = new int[m];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sum_grades[i] += grades[i][j];
			}
		}

		int[][] dp = new int[m + 1][n * x + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = prices[i]; j <= n * x; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prices[i]] + sum_grades[i]);
				if (j >= prices[i]) { // nu am mai comandat un platou de tip i pana atunci
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - prices[i]] + sum_grades[i]);
				}
			}
		}
		return dp[n][n * x];
	}

	static int task2() {
		// TODO solve task 2
		return 0;
	}

	static int task3() {
		// TODO solve task 3
		return 0;
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("sushi.in"));

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
