import java.util.*;
import java.io.*;

public class _1466_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] w = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			long cur = 0;
			for(int j = 0; j < n; j++) {
				w[j] = Integer.parseInt(line.nextToken());
				cur += w[j];
			}
			int[] nodes = new int[n];
			for(int j = 0; j < n - 1; j++) {
				line = new StringTokenizer(in.readLine());
				int v1 = Integer.parseInt(line.nextToken()) - 1;
				int v2 = Integer.parseInt(line.nextToken()) - 1;
				nodes[v1]++;
				nodes[v2]++;
			}
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			for(int j = 0; j < n; j++) {
				if(nodes[j] > 1) {
					for(int k = 0; k < nodes[j] - 1; k++) {
						pq.add(w[j]);
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append(cur);
			sb.append(' ');
			for(int j = 1; j < n - 1; j++) {
				cur += pq.poll();
				sb.append(cur);
				sb.append(' ');
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
