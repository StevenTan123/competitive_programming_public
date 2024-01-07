import java.util.*;
import java.io.*;

public class _1420_C2 {
    static long mins, maxes;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            mins = 0;
            maxes = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int q = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            for(int i = 0; i < n; i++) {
                if(min(a, i)) {
                    mins += a[i];
                }
                if(max(a, i)) {
                    maxes += a[i];
                }
            }
            out.println(maxes - mins);
            for(int i = 0; i < q; i++) {
                line = new StringTokenizer(in.readLine());
                int l = Integer.parseInt(line.nextToken()) - 1;
                int r = Integer.parseInt(line.nextToken()) - 1;
                int temp = a[l];
                update(a, l, a[r]);
                update(a, r, temp);
                out.println(maxes - mins);
            }
        }
        in.close();
        out.close();
    }
    static void update(int[] a, int i, int val) {
        for(int j = i - 1; j <= i + 1; j++) {
            if(j >= 0 && j < a.length) {
                if(min(a, j)) {
                    mins -= a[j];
                }
                if(max(a, j)) {
                    maxes -= a[j];
                }
            }
        }
        a[i] = val;
        for(int j = i - 1; j <= i + 1; j++) {
            if(j >= 0 && j < a.length) {
                if(min(a, j)) {
                    mins += a[j];
                }
                if(max(a, j)) {
                    maxes += a[j];
                }
            }
        }
    }
    static boolean min(int[] a, int i) {
        int left = i > 0 ? a[i - 1] : 0;
        int right = i < a.length - 1 ? a[i + 1] : 0;
        return a[i] < left && a[i] < right;
    }
    static boolean max(int[] a, int i) {
        int left = i > 0 ? a[i - 1] : 0;
        int right = i < a.length - 1 ? a[i + 1] : 0;
        return a[i] > left && a[i] > right;
    }
}
