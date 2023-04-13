import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;

public class TEST {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

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

      /*  int N = a.length;
        int sum = 0;
        ArrayList<Integer> maxArr = new ArrayList<>(N); // construiesc un vector care retine val maxime ale perechilor
        for (int i = 0; i < N; i++) {
            maxArr.add(Math.max(a[i], b[i]));
        }
        Collections.sort(maxArr);

        ArrayList<Integer> allNumbers = new ArrayList<>(2 * N); // vectorul care o sa contina sirurile concatenate ordonate
        Arrays.stream(a).forEach(i -> allNumbers.add(i));
        ArrayList<Integer> bList = new ArrayList<>(N);
        Arrays.stream(b).forEach(i -> bList.add(i));
        allNumbers.addAll(bList);
        Collections.sort(allNumbers); // sortez si adaug toate elementele intr-un array
        int j = 0;

        for (int i = 2 * N - 1; i >= 0 && moves > 0; i--) {
            if (!maxArr.contains(allNumbers.get(i))) {
                maxArr.set(j, allNumbers.get(i));
                moves--;
                j++;
            }
        }

        for (int i = 0; i < N; i++) {
            sum += maxArr.get(i);

        }*/
        int N = a.length;
        int sum = 0;
        int[] maxArr = new int[N]; // construiesc un vector care retine val maxime ale perechilor
        for (int i = 0; i < N; i++) {
            maxArr[i] = Math.max(a[i], b[i]);
        }

        Arrays.sort(maxArr);

        int[] allNumbers = new int[2 * N]; // vectorul care o sa contina sirurile concatenate ordonate
        System.arraycopy(a, 0, allNumbers, 0, N);
        System.arraycopy(b, 0, allNumbers, N, N);
        Arrays.sort(allNumbers);
        int j = 0;
        int contor = N - 1;

        for (int i = 2 * N - 1; i >= 0 && moves > 0 && contor >= j; i--) {
            if (allNumbers[i] != maxArr[contor]) {
                System.out.println("schimba in max arr pe: " + maxArr[j] + " cu " + allNumbers[i]);
                maxArr[j] = allNumbers[i];
                moves--;
                j++;
            } else {
                contor--;
            }
        }

        for (int i = 0; i < N; i++) {
            sum += maxArr[i];
        }
        System.out.println(sum);
    }

}
