import java.util.*;
import java.io.*;

public class _1853_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int res = 0;
            for (int i = 0; i <= n / 2; i++) {
                int prev1 = n - i;
                int prev2 = i;
                int count = 3;
                if (k == 3) {
                    res++;
                    continue;
                }
                while (prev2 > 0) {
                    int temp = prev2;
                    prev2 = prev1 - prev2;
                    prev1 = temp;
                    if (prev2 > prev1) {
                        count = -1;
                        break;
                    }
                    count++;
                    if (count >= k) {
                        break;
                    }
                }
                if (prev2 >= 0 && count >= k) {
                    res++;
                }
            }
            out.println(res);
        }
		
        in.close();
		out.close();
	}
}
