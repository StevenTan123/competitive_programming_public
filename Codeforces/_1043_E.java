import java.util.*;
import java.io.*;

public class _1043_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] scores = new int[n][2];
        int[][] sub = new int[n][2];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            scores[i] = new int[] {Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
            sub[i][0] = scores[i][1] - scores[i][0];
            sub[i][1] = i;
        }
        Arrays.sort(sub, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] > b[0]) return 1;
                else if(a[0] < b[0]) return -1;
                return 0;
            }
        });
        long[][] sums = new long[n][2];
        for(int i = 0; i < n; i++) {
            sums[i][0] = (i == 0 ? 0 : sums[i - 1][0]) + scores[sub[i][1]][0];
            sums[i][1] = (i == 0 ? 0 : sums[i - 1][1]) + scores[sub[i][1]][1];
        }
        long[] res = new long[n];
        for(int i = 0; i < n; i++) {
            long total = (long)scores[sub[i][1]][1] * (n - i - 1) + (long)scores[sub[i][1]][0] * i;
            total += (i == 0 ? 0 : sums[i - 1][1]) + (sums[n - 1][0] - sums[i][0]);
            res[sub[i][1]] = total;
        }
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int p1 = Integer.parseInt(line.nextToken()) - 1;
            int p2 = Integer.parseInt(line.nextToken()) - 1;
            int score = Math.min(scores[p1][0] + scores[p2][1], scores[p1][1] + scores[p2][0]);
            res[p1] -= score;
            res[p2] -= score;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(res[i]);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
}
