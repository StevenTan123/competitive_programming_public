import java.util.*;
import java.io.*;

public class equal_sum_gold {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int N = Integer.parseInt(in.readLine());
        long[] a = new long[N];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(line.nextToken());
        }

        long[][] rsums = new long[N * (N + 1) / 2][2];
        int[][] ps = new int[N][N];
        int p = 0;
        for (int i = 0; i < N; i++) {
            long sum = 0;
            for (int j = i; j < N; j++) {
                ps[i][j] = p;
                sum += a[j];
                rsums[p][0] = sum;
                rsums[p][1] = p;
                p++;
            }
        }
        Arrays.sort(rsums, new Comparator<long[]>() {
            @Override
            public int compare(long[] a, long[] b) {
                if (a[0] > b[0]) {
                    return 1;
                } else if (a[0] < b[0]) {
                    return -1;
                }
                return 0;
            }
        });
        long[] gaps = new long[rsums.length];
        for (int i = 1; i < rsums.length; i++) {
            gaps[i] = rsums[i][0] - rsums[i - 1][0];
        }

        int[] inds = new int[N * (N + 1) / 2];
        for (int i = 0; i < rsums.length; i++) {
            inds[(int) rsums[i][1]] = i;
        }

        // around[i] says if the i-th largest subarray sum is around the wanted index
        boolean[] around = new boolean[p];

        for (int i = 0; i < N; i++) {
            out.println(min_change(N, ps, inds, gaps, around, i));
        }

        in.close();
        out.close();
    }

    static long min_change(int N, int[][] ps, int[] inds, long[] gaps, boolean[] around, int ind) {
        for (int i = 0; i < N; i++) {
            if (i < ind) {
                around[inds[ps[i][ind - 1]]] = false;
            } else {
                around[inds[ps[ind][i]]] = true;
            }
        }
        long min = Long.MAX_VALUE;
        for (int i = 1; i < around.length; i++) {
            if (around[i] != around[i - 1]) {
                min = Math.min(min, gaps[i]);
            }
        }
        return min;
    }
}