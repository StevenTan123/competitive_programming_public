import java.util.*;
import java.io.*;

public class _1850_F {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            int[] count = new int[n + 1];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(line.nextToken());
                if (val <= n) {
                    count[val]++;
                }
            }
            int res = 0;
            int[] lands = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j += i) {
                    lands[j] += count[i];
                    res = Math.max(res, lands[j]);
                }
            }
            out.println(res);
		}
		
        in.close();
		out.close();
	}
}
