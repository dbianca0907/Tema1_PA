/*
 * Acest schelet citește datele de intrare și scrie răspunsul generat de voi,
 * astfel că e suficient să completați cele două metode.
 *
 * Scheletul este doar un punct de plecare, îl puteți modifica oricum doriți.
 *
 * Dacă păstrați scheletul, nu uitați să redenumiți clasa și fișierul.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Nostory {
	public static void main(final String[] args) throws IOException {
		var scanner = new MyScanner(new FileReader("nostory.in"));

		var task = scanner.nextInt();
		var n = scanner.nextInt();
		var moves = task == 2 ? scanner.nextInt() : 0;

		var a = new int[n];
		for (var i = 0; i < n; i += 1) {
			a[i] = scanner.nextInt();
		}

		var b = new int[n];
		for (var i = 0; i < n; i += 1) {
			b[i] = scanner.nextInt();
		}

		try (var printer = new PrintStream("nostory.out")) {
			if (task == 1) {
				printer.println(solveTask1(a, b));
			} else {
				printer.println(solveTask2(a, b, moves));
			}
		}
	}

	private static long solveTask1(int[] a, int[] b) {
		int N = 2 * a.length;
		int[] res = new int[N];
		System.arraycopy(a, 0, res, 0, a.length);
		System.arraycopy(b, 0, res, a.length, b.length);

		Arrays.sort(res);
		long sum = 0;

		for (int i = N - 1; i >= N / 2; i--) {
			sum += res[i];
		}
		return sum;
	}

	private static long solveTask2(int[] a, int[] b, int moves) {
		int N = a.length;
		long sum = 0;
		int[] maxArr = new int[N]; // construiesc un vector care retine val maxime ale perechilor
		for (int i = 0; i < N; i++) {
			maxArr[i] = Math.max(a[i], b[i]);
		}

		Arrays.sort(maxArr);

		int[] allNumbers = new int[2 * N]; // vectorul care o sa contina sirurile concatenate ordonate
		System.arraycopy(a, 0, allNumbers, 0, N);
		System.arraycopy(b, 0, allNumbers, N, N);
		Arrays.sort(allNumbers);
		int j = 0; // de unde incep sa fie schimbate pozitiile minime
		int cnt = N - 1;

		for (int i = 2 * N - 1; i >= 0 && moves > 0 && cnt >= j; i--) {
			if (allNumbers[i] != maxArr[cnt]) {
				maxArr[j] = allNumbers[i];
				moves--;
				j++;
			} else {
				cnt--;
			}
		}

		for (int i = 0; i < N; i++) {
			sum += maxArr[i];
		}
		return sum;
	}

	/**
	 * A class for buffering read operations, inspired from here:
	 * https://pastebin.com/XGUjEyMN.
	 */
	private static class MyScanner {
		private BufferedReader br;
		private StringTokenizer st;

		public MyScanner(Reader reader) {
			br = new BufferedReader(reader);
		}

		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
