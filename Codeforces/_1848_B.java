import java.util.*;
import java.io.*;

public class _1848_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] c = new int[n];

            int[] prev = new int[k];
            ArrayList<Integer>[] gaps = new ArrayList[k];
            for (int i = 0; i < k; i++) {
                gaps[i] = new ArrayList<Integer>();
            }
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(line.nextToken()) - 1;
                gaps[c[i]].add(i - prev[c[i]]);
                prev[c[i]] = i + 1;
            }
            for (int i = 0; i < k; i++) {
                gaps[i].add(n - prev[i]);
                Collections.sort(gaps[i]);
            }

            int min_gap = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                int max_gap = gaps[i].get(gaps[i].size() - 1) / 2;
                if (gaps[i].size() > 1) {
                    max_gap = Math.max(max_gap, gaps[i].get(gaps[i].size() - 2));
                }
                min_gap = Math.min(min_gap, max_gap);
            }
            out.println(min_gap);
        }
		
        in.close();
		out.close();
	}
}
