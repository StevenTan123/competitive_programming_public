import java.util.*;
import java.io.*;

public class _1850_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            ArrayList<Integer> a = new ArrayList<Integer>();
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a.add(Integer.parseInt(line.nextToken()));
            }
            Collections.sort(a);
            int max_gap = 0;
            int prev = -1;
            for (int i = 0; i < n; i++) {
                int gap = i == n - 1 ? k + 1 : a.get(i + 1) - a.get(i);
                if (gap > k) {
                    if (prev == -1) {
                        max_gap = Math.max(max_gap, i + 1);
                    } else {
                        max_gap = Math.max(max_gap, i - prev);
                    }
                    prev = i;
                }
            }
            out.println(n - max_gap);
        }
		
        in.close();
		out.close();
	}
}
