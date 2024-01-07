import java.util.*;
import java.io.*;

public class _670_D1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        int[] b = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(line.nextToken());
        }
        for(int i = 0; i < k; i++) {
            int[] mins = new int[n];
            int min = 0;
            for(int j = 0; j < n; j++) {
                mins[j] = b[j] / a[j];
                if(mins[j] < mins[min]) {
                    min = j;
                }
            }
            b[min]++;
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            res = Math.min(res, b[i] / a[i]);
        }
        out.println(res);
        in.close();
        out.close();
    }
}
