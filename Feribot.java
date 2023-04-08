import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Feribot {
    public static int num_intervals(ArrayList<Long> cars, int N, long total) {
        long indx = 0;
        int j = 0;
        long sum = 0;
        int nr = 0;

        while (indx < N) {
            sum = 0;
            while (j < N && sum + cars.get(j) <= total) {
                sum += cars.get(j);
                j++;
            }
            nr++;
            indx = j;
        }

        return nr;
    }

    public static long find_max_cost (ArrayList<Long> cars, int N, int k, long sum) {
        ArrayList<Long> carsClone;
        carsClone = (ArrayList<Long>) cars.clone();
        Collections.sort(carsClone);
        long max = carsClone.get(N - 1);
        long left = max;
        long right = sum;
        long mid;
        long found = -1;

        while (left <= right) {
            mid = (left + right) / 2;
            int intervals = num_intervals(cars, N, mid);
            if (intervals <= k) { // numarul este prea mare
                right = mid - 1;
                found = mid;
            } else {
                left = mid + 1;
            }
        }
        return found;
    }
    public static void main(final String[] args) throws IOException {
        var scanner = new MyScanner(new FileReader("feribot.in"));

        int N = scanner.nextInt();
        int k = scanner.nextInt();

        ArrayList<Long> cars = new ArrayList<>(N + 1);
        long sum = 0;
        for (int i = 0; i < N; i++) {
            cars.add(scanner.nextLong());
            sum += cars.get(i);
        }

        long max_cost = find_max_cost(cars, N, k, sum);
        var printer = new PrintStream("feribot.out");
        printer.println(max_cost);
    }


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
