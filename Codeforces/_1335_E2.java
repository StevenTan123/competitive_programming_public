import java.util.*;
import java.io.*;

public class _1335_E2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken()) - 1;
            }
            int[][] prefix = new int[n][200];
            ArrayList<Integer>[] occurences = new ArrayList[200];
            for(int i = 0; i < 200; i++) occurences[i] = new ArrayList<Integer>();
            for(int i = 0; i < n; i++) {
                occurences[a[i]].add(i);
                for(int j = 0; j < 200; j++) {
                    prefix[i][j] = i == 0 ? 0 : prefix[i - 1][j];
                }
                prefix[i][a[i]]++;
            }
            int res = 0;
            for(int i = 0; i < 200; i++) {
                for(int j = 0; (j + 1) * 2 <= occurences[i].size(); j++) {
                    int l = occurences[i].get(j);
                    int r = occurences[i].get(occurences[i].size() - j - 1);
                    int maxmid = 0;
                    for(int k = 0; k < 200; k++) {
                        maxmid = Math.max(maxmid, prefix[r - 1][k] - prefix[l][k]);
                    }
                    res = Math.max(res, (j + 1) * 2 + maxmid);
                }
            }
            int fmid = 0;
            for(int i = 0; i < 200; i++) {
                fmid = Math.max(fmid, prefix[n - 1][i]);
            }
            res = Math.max(res, fmid);
            out.println(res);
        }
        in.close();
        out.close();
    }
}
