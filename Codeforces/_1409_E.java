import java.util.*;
import java.io.*;

public class _1409_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] x = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(line.nextToken());
            }
            in.readLine();
            Random rand = new Random();
            for(int i = 0; i < n; i++) {
                int randint = rand.nextInt(n);
                int temp = x[i];
                x[i] = x[randint];
                x[randint] = temp;
            }
            Arrays.sort(x);
            int[] prefix = new int[n];
            int rpointer = 0;
            for(int i = 0; i < n; i++) {
                while(rpointer < n && x[rpointer] - x[i] <= k) {
                    rpointer++;
                }
                prefix[rpointer - 1] = Math.max(prefix[rpointer - 1], rpointer - i);
            }
            int[] pmax = new int[n];
            for(int i = 0; i < n; i++) {
                pmax[i] = Math.max(i == 0 ? 0 : pmax[i - 1], prefix[i]);
            }
            rpointer = 0;
            int res = 0;
            for(int i = 0; i < n; i++) {
                while(rpointer < n && x[rpointer] - x[i] <= k) {
                    rpointer++;
                }
                res = Math.max(res, rpointer - i + (i == 0 ? 0 : pmax[i - 1]));
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
