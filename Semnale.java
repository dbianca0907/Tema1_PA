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
		int n = x + y; //nr total de numere
		// Nr de siruri corecte care se termina in 0.
		int[][] seq_0 = new int[n + 1][n + 1];
		// Nr de siruri corecte care se termina in 1.
		int[][] seq_1 = new int[n + 1][n + 1];

		// Se poate forma un singur sir doar din cifra 1.
		seq_1[1][0] = 1;
		// Se poate forma un singur sir numai din zerouri
		seq_0[1][1] = 1;
		if (y > n / 2) {
			return 0;
		}

		for (int i = 2; i <= n; i++) {
			int min = Math.min(i, x);
			// Ma opresc dupa ce calculez numarul de siruri pentru x.
			for (int j = 1; j <= min; j++) {
				if (i == j) {
					// Exista un singur sir care se poate forma numai din 0-uri.
					seq_0[i][j] = 1;
					// Nu exista niciun sir format numai din 0-uri si sa se termine in 1.
					seq_1[i][j] = 0;
				} else {
					seq_0[i][j] = (seq_0[i - 1][j - 1] + seq_1[i - 1][j - 1]) % mod;
					seq_1[i][j] = (seq_0[i - 1][j]) % mod;
				}
			}
		}
		/* Rezultatul este format din numarul de siruri care se termina in 0 cu x zerouri
		si numarul de siruri care se termina in 1 care contin x zerouri. */
		int rez = (seq_0[n][x] + seq_1[n][x]) % mod;
		return rez;
	}

	static int type2() {
		int n = x + y;
		// Nr de siruri corecte care se termina in 0.
		int[][] seq_0 = new int[n + 1][n + 1];
		// Nr de siruri corecte care se termina in 1.
		int[][] seq_1 = new int[n + 1][n + 1];

		// Se poate forma un singur sir cu un singur 1.
		seq_1[1][0] = 1;
		// Se poate forma un singur sir cu 2 de 1 ("11").
		seq_1[2][0] = 1;
		/* Se poate forma un singur sir cu un 0, care
		sa se termine in 1. */
		seq_0[1][1] = 1;

		if (y > 2 * (x + 1)) {
			return 0;
		}

		for (int i = 2; i <= n; i++) {
			int min = Math.min(i, x);
			for (int j = 1; j <= min; j++) {
				if (i == j) {
					seq_0[i][j] = 1; // se poate forma un singur sir doar din 0-uri
				} else {
					seq_0[i][j] = (seq_0[i - 1][j - 1] + seq_1[i - 1][j - 1]) % mod;
					seq_1[i][j] = (seq_0[i - 1][j] + seq_0[i - 2][j]) % mod;
				}
			}
		}
		/* Rezultatul este format din numarul de siruri care se termina in 0 cu x zerouri
		si numarul de siruri care se termina in 1 care contin x zerouri. */
		int rez = (seq_0[n][x] + seq_1[n][x]) % mod;
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
