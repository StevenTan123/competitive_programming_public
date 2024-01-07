import java.util.*;
import java.io.*;

public class _1541_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] d = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                d[i] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(d);
            int[] dif = new int[n - 1];
            for(int i = 1; i < n; i++) {
                dif[i - 1] = d[i] - d[i - 1];
            }
            long res = 0;
            for(int i = 0; i < n - 1; i++) {
                long contained = chooseTwo(n) - chooseTwo(i + 1) - chooseTwo(n - (i + 1));
                res += dif[i];
                res -= contained * dif[i];
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long chooseTwo(int x) {
        return (long)x * (x - 1) / 2;
    }
}
