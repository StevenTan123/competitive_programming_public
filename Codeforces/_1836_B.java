import java.util.*;
import java.io.*;

public class _1836_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int g = Integer.parseInt(line.nextToken());
            long total = (long) k * g;
            
            if (n == 1) {
                out.println(0);
                continue;
            }

            int save_one = g / 2;
            if (g % 2 == 0) {
                save_one--;
            }
            long save = (long) save_one * n;
            if (save > total) {
                out.println(total);
            } else {
                long res = (long) save_one * (n - 1);
                long last = total - res;
                if (last % g > save_one) {
                    res += last - (last + g - (last % g));
                } else {
                    res += last - (last - (last % g));
                }
                out.println(res);
            }
        }

		in.close();
		out.close();
	}
}
