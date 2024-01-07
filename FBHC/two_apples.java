import java.util.*;
import java.io.*;

public class two_apples {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("two_apples_a_day_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int N = Integer.parseInt(in.readLine());
            int[] A = new int[2 * N - 1];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < 2 * N - 1; i++) {
                A[i] = Integer.parseInt(line.nextToken());
            }
            String res = "Case #" + t + ": ";
            if (N == 1) {
                out.println(res + 1);
            } else {
                Arrays.sort(A);

                int left = buy_missing(A, N, A[1] + A[A.length - 1]);
                int right = buy_missing(A, N, A[0] + A[A.length - 2]);
                int middle = buy_missing(A, N, A[0] + A[A.length - 1]);

                int min = Math.min(left, Math.min(right, middle));
                out.println(res + (min == Integer.MAX_VALUE ? "-1" : min));
            }
        }
        in.close();
        out.close();
    }
    static int buy_missing(int[] A, int N, int sum) {
        int l = 0;
        int r = A.length - 1;
        int missing = Integer.MAX_VALUE;
        while (l <= r) {
            if (l == r) {
                if (missing == Integer.MAX_VALUE && sum - A[l] > 0) {
                    missing = sum - A[l];
                } else {
                    return Integer.MAX_VALUE;
                }
                break;
            } else if (A[l] + A[r] < sum) {
                if (missing == Integer.MAX_VALUE && sum - A[l] > 0) {
                    missing = sum - A[l];
                } else {
                    return Integer.MAX_VALUE;
                }
                l++;
            } else if (A[l] + A[r] > sum) {
                if (missing == Integer.MAX_VALUE && sum - A[r] > 0) {
                    missing = sum - A[r];
                } else {
                    return Integer.MAX_VALUE;
                }
                r--;
            } else {
                l++;
                r--;
            }
        }
        return missing;
    }
}
