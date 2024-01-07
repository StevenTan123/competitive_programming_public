import java.util.*;
import java.io.*;

public class _1556_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        long res = 0;
        for(int i = 0; i < n; i += 2) {
            long sum = 0;
            long min = Long.MAX_VALUE;
            for(int j = i; j < n; j++) {
                long most = min;
                if(j % 2 == 0) {
                    sum += a[j];
                }else {
                    sum -= a[j];
                }
                if(j == i) {
                    min = Math.min(min, sum - 1);
                }else {
                    min = Math.min(min, sum);
                }
                long least = Math.max(0, sum);
                if(j % 2 == 1) {
                    if(most >= least) {
                        res += most - least + 1;
                    }
                }
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
}
