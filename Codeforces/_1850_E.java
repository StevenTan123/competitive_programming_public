import java.util.*;
import java.io.*;

public class _1850_E {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            long c = Long.parseLong(line.nextToken());
            int[] s = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(line.nextToken());
            }
            int l = 0;
            int r = 500000000;
            int res = -1;
            while (l <= r) {
                int m = (l + r) / 2;
                long sum = 0;
                for (int i = 0; i < n; i++) {
                    long add = (long)(s[i] + 2 * m) * (s[i] + 2 * m);
                    if (Long.MAX_VALUE - add < sum) {
                        sum = Long.MAX_VALUE;
                        break;
                    }
                    sum += add;
                }
                if (sum > c) {
                    r = m - 1;
                } else if (sum < c) {
                    l = m + 1;
                } else {
                    res = m;
                    break;
                }
            }
            out.println(res);
        }
		
        in.close();
		out.close();
	}
}
