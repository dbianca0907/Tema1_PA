import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Semnale {
	static int sig_type, x, y;
	static final int  mod = 1000000007;

	Semnale(){}

	static int type1() {
		int n = x + y; //nr total de biti
		int[][] a_1 = new int[n + 1][n + 1]; // nr de siruri corecte care se termina in 0
		int[][] b_1 = new int[n + 1][n + 1]; // nr de siruri corecte care se termina in 1

		b_1[1][0] = 1;
		b_1[1][1] = 1;
		a_1[1][1] = 1;
		if (y > n / 2) {
			return 0;
		}

		for (int i = 2; i <= n; i++) {
			int min = Math.min(i, x);
			for (int j = 1; j <= min; j++) {
				if (i == j) {
					a_1[i][j] = 1; // exista un singur sir care se poate forma numai din 0-uri
					b_1[i][j] = 0; // nu se poate forma niciun sir numai din 1
				} else {
					a_1[i][j] = (a_1[i - 1][j - 1] + b_1[i - 1][j - 1]) % mod;
					b_1[i][j] = (a_1[i - 1][j]) % mod; // peste 0-urile alea se adauga un 1
				}
			}
		}
		int rez = (a_1[n][x] + b_1[n][x]) % mod; // de explicat x-ul de aici
		return rez;
	}

	static int type2() {
		int n = x + y;
		// Nr de siruri corecte care se termina in 0.
		int[][] a = new int[n + 1][n + 1];
		// Nr de siruri corecte care se termina in 1.
		int[][] b = new int[n + 1][n + 1];

		b[1][0] = 1;
		b[2][0] = 1;
		a[1][1] = 1;

		if (y > 2 * (x + 1)) {
			return 0;
		}

		for (int i = 2; i <= n; i++) {
			int min = Math.min(i, x);
			for (int j = 1; j <= min; j++) {
				if (i == j) {
					a[i][j] = 1;
				} else {
					a[i][j] = (a[i - 1][j - 1] + b[i - 1][j - 1]) % mod;
					b[i][j] = (a[i - 1][j] + a[i - 2][j]) % mod;
				}
			}
		}
		int rez = (a[n][x] + b[n][x]) % mod;
		return rez;
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("semnale.in"));

			sig_type = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();

			int ans;
			switch (sig_type) {
				case 1:
					ans = Semnale.type1();
					break;
				case 2:
					ans = Semnale.type2();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("semnale.out");
				fw.write(Integer.toString(ans));
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
