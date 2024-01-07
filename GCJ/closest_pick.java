import java.util.*;
import java.io.*;

public class closest_pick {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n + 2];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            a[n] = 0;
            a[n + 1] = k + 1;
            Arrays.sort(a);
            int[] gaps = new int[n + 1];
            int[] sing = new int[n + 1];
            for(int i = 1; i < n + 2; i++) {
                int whole = a[i] - a[i - 1] - 1;
                if(whole < 0) {
                    gaps[i - 1] = 0;
                    sing[i - 1] = 0;
                    continue;
                }
                if(i > 1 && i < n + 1) {
                    int gap = whole / 2;
                    if(whole % 2 != 0) gap++;
                    gaps[i - 1] = gap;
                }else {
                    gaps[i - 1] = whole;
                }
                sing[i - 1] = whole;
            }
            Arrays.sort(gaps);
            Arrays.sort(sing);
            int win = Math.max(gaps[n] + gaps[n - 1], sing[n]);
            double ans = (double)win / k;
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
}
