import java.util.*;
import java.io.*;

public class _1552_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int rep = n / (k - 1);
        if(n % (k - 1) != 0) {
            rep++;
        }
        int[] a = new int[n * k];
        int[] prev = new int[n + 1];
        Arrays.fill(prev, -1);
        int[][] segs = new int[n * (k - 1)][3];
        int p = 0;
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n * k; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            if(prev[a[i]] != -1) {
                segs[p] = new int[] {prev[a[i]], i, a[i]};
                p++;
            }
            prev[a[i]] = i;
        }
        Arrays.sort(segs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int[] counts = new int[n * k];
        boolean[] used = new boolean[n + 1];
        int[][] res = new int[n + 1][2];
        for(int i = 0; i < segs.length; i++) {
            if(!used[segs[i][2]]) {
                boolean good = true;
                for(int j = segs[i][0]; j <= segs[i][1]; j++) {
                    if(counts[j] >= rep) {
                        good = false;
                        break;
                    }
                }
                if(good) {
                    for(int j = segs[i][0]; j <= segs[i][1]; j++) {
                        counts[j]++;
                    }
                    used[segs[i][2]] = true;
                    res[segs[i][2]] = new int[] {segs[i][0] + 1, segs[i][1] + 1};
                }
            }
        }
        for(int i = 1; i <= n; i++) {
            out.println(res[i][0] + " " + res[i][1]);
        }
        in.close();
        out.close();
    }
}
