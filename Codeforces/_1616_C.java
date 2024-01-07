import java.util.*;
import java.io.*;

public class _1616_C {
    static double epsilon = 1e-9;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int min = n - 1;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    double dif = a[j] - a[i];
                    if(j < i) {
                        dif *= -1;
                    }
                    if(Math.abs(i - j) > 0) {
                        dif /= Math.abs(i - j);
                    }
                    min = Math.min(min, change(a, i, dif));
                }
            }
            out.println(min);
        }
        in.close();
        out.close();
    }
    static int change(int[] a, int ind, double dif) {
        int ret = 0;
        for(int i = 0; i < a.length; i++) {
            double cur = a[ind] + (i - ind) * dif;
            if(Math.abs(cur - a[i]) > epsilon) {
                ret++;
            }
        }
        return ret;
    }
}
