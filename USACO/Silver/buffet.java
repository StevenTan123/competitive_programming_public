import java.util.*;
import java.io.*;

public class buffet {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("buffet.in"));
		PrintWriter out = new PrintWriter("buffet.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int e = Integer.parseInt(line.nextToken());
		int[][] sorted = new int[n][2];
		ArrayList<Integer>[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			sorted[i][0] = Integer.parseInt(line.nextToken());
			sorted[i][1] = i;
			int d = Integer.parseInt(line.nextToken());
			for(int j = 0; j < d; j++) {
				graph[i].add(Integer.parseInt(line.nextToken()) - 1);
			}
		}
		Arrays.sort(sorted, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});
		int[] dp = new int[n];
		int max = 0;
		for(int i = 0; i < n; i++) {
			int[] dists = bfs(graph, sorted[i][1]);
			dp[i] = sorted[i][0];
			for(int j = 0; j < i; j++) {
				if(sorted[j][0] < sorted[i][0] && dists[sorted[j][1]] != -1) {
					dp[i] = Math.max(dp[i], dp[j] + sorted[i][0] - e * dists[sorted[j][1]]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		out.println(max);
		in.close();
		out.close();
	}
	static int[] bfs(ArrayList<Integer>[] graph, int source) {
		int[] dists = new int[graph.length];
		Arrays.fill(dists, -1);
		ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
		bfs.add(new BFS(source, 0));
		while(bfs.size() > 0) {
			BFS cur = bfs.poll();
			if(dists[cur.n] != -1) continue;
			dists[cur.n] = cur.d;
			for(int nei : graph[cur.n]) {
				bfs.add(new BFS(nei, cur.d + 1));
			}
		}
		return dists;
	}
	static class BFS {
		int n, d;
		BFS(int nn, int dd) {
			n = nn;
			d = dd;
		}
	}
}
