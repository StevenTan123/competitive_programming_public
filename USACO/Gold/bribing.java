import java.util.*;
import java.io.*;

public class bribing {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int A = Integer.parseInt(line.nextToken());
        int B = Integer.parseInt(line.nextToken());

        int[][] cows = new int[N][3];
        for (int i = 0; i < N; i++) {
            line = new StringTokenizer(in.readLine());
            for (int j = 0; j < 3; j++) {
                cows[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        Arrays.sort(cows, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });

        //dp[i][j] = max popularity bribing friends 1...i only using j cones
        int[][] dp = new int[N + 1][B + 1];

        //dp2[i][j] = max popularity bribing friends 1...i using j mooneys (after discounting)
        int[][] dp2 = new int[N + 1][A + 1];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= B; j++) {
                dp[i + 1][j] = Math.max(dp[i][j], dp[i + 1][j]);
                int max_discount = (B - j) / cows[i][2];
                if (max_discount > cows[i][1]) {
                    dp[i + 1][j + cows[i][1] * cows[i][2]] = Math.max(dp[i + 1][j + cows[i][1] * cows[i][2]], dp[i][j] + cows[i][0]);
                    dp2[i + 1][0] = Math.max(dp2[i + 1][0], dp[i + 1][j + cows[i][1] * cows[i][2]]);
                } else {
                    if (cows[i][1] - max_discount <= A) {
                        dp2[i + 1][cows[i][1] - max_discount] = Math.max(dp2[i + 1][cows[i][1] - max_discount], dp[i][j] + cows[i][0]);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= A; j++) {
                dp2[i + 1][j] = Math.max(dp2[i][j], dp2[i + 1][j]);
                if (A - j >= cows[i][1]) {
                    dp2[i + 1][j + cows[i][1]] = Math.max(dp2[i + 1][j + cows[i][1]], dp2[i][j] + cows[i][0]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= A; i++) {
            max = Math.max(max, dp2[N][i]);
        }
        out.println(max);

        in.close();
        out.close();
    }
}