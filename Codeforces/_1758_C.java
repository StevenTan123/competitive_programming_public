import java.util.*;
import java.io.*;

public class _1758_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            int[] p = new int[n];
            for(int i = 1; i < n - 1; i++) {
                p[i] = i + 1;
            }
            p[0] = n;
            p[n - 1] = 1;
            if(n % x == 0) {
                if(x != n) {
                    int num = n / x;
                    ArrayList<Integer> factors = new ArrayList<Integer>();
                    for(int i = 2; i * i <= num; i++) {
                        while(num % i == 0) {
                            factors.add(i);
                            num /= i;
                        }
                    }
                    if(num >= 2) {
                        factors.add(num);
                    }
                    Collections.sort(factors);
                    p[0] = x;
                    int cur = x;
                    for(int i = 0; i < factors.size(); i++) {
                        p[cur - 1] = cur * factors.get(i);
                        cur *= factors.get(i);
                    }
                }
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < n; i++) {
                    sb.append(p[i]);
                    sb.append(' ');
                }
                out.println(sb.toString());
            }else {
                out.println(-1);
            }
        }
        in.close();
        out.close();
    }
}
