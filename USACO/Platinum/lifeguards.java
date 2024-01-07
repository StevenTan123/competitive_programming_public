import java.util.*;
import java.io.*;

public class lifeguards {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter out = new PrintWriter("lifeguards.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        Interval[] intervals = new Interval[n];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            intervals[i] = new Interval(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
        }
        Arrays.sort(intervals);
        ArrayList<Interval> ints = new ArrayList<Interval>();
        int max = 0;
        for(int i = 0; i < n; i++) {
            if(intervals[i].b > max) {
                ints.add(intervals[i]);
                max = intervals[i].b;
            }
        }
        k = Math.max(0, k - (n - ints.size()));
        int[][] dp = new int[ints.size()][k + 1];
        int[][] best = new int[ints.size()][k + 1];
        int p = 0;
        for(int i = 0; i < ints.size(); i++) {
            while(ints.get(p).b < ints.get(i).a) {
                p++;
            }
            for(int j = 0; j <= k; j++) {
                if(j > i) {
                    continue;
                }
                if(p < i) {
                    int skip = i - p - 1;
                    int ind = Math.max(0, j - skip);
                    dp[i][j] = Math.max(dp[i][j], dp[p][ind] + ints.get(i).b - ints.get(p).b);
                }
                if(p > 0) {
                    int skip = i - p;
                    int ind = Math.max(0, j - skip);
                    dp[i][j] = Math.max(dp[i][j], best[p - 1][ind] + ints.get(i).b - ints.get(i).a);
                }else if(i == j){
                    dp[i][j] = Math.max(dp[i][j], ints.get(i).b - ints.get(i).a);
                }
                if(i == 0 || j == 0) {
                    best[i][j] = dp[i][j];
                }else {
                    best[i][j] = Math.max(best[i - 1][j - 1], dp[i][j]);
                }
            }
        }
        out.println(best[ints.size() - 1][k]);
        in.close();
        out.close();
    }
    static class Interval implements Comparable<Interval> {
        int a, b;
        Interval(int aa, int bb) {
            a = aa;
            b = bb;
        }
        @Override
        public int compareTo(Interval o) {
            return a - o.a;
        }
    }
}
