import java.util.*;
import java.io.*;

public class _1853_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int min_gap = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if (i > 0) {
                    min_gap = Math.min(min_gap, a[i] - a[i - 1]);
                }
            }
            if (min_gap < 0) {
                out.println(0);
            } else {
                out.println(min_gap / 2 + 1);
            }
        }
		
        in.close();
		out.close();
	}
}
