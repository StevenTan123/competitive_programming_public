import java.util.*;
import java.io.*;

public class _1361_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			graph[v1].add(v2);
			graph[v2].add(v1);
		}
		int[][] t = new int[n][2];
		boolean possible = true;
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			t[i][0] = Integer.parseInt(line.nextToken());
			t[i][1] = i;
		}
		int[] visited = new int[n + 1];
		Arrays.fill(visited, -1);
		for(int i = 0; i < n; i++) {
			ArrayList<Integer> neighbors = graph[i];
			for(int neighbor : neighbors) {
				if(t[i][0] == t[neighbor][0]) {
					possible = false;
					break;
				}
				visited[t[neighbor][0]] = i;
			}
			if(possible == false) break;
			for(int j = 1; j < t[i][0]; j++) {
				if(visited[j] != i) {
					possible = false;
					break;
				}
			}
			if(possible == false) break;
		}
		if(!possible) {
			out.println(-1);
		}else {
			Arrays.sort(t, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			for(int i = 0; i < n; i++) {
				out.print(t[i][1] + 1 + " ");
			}
		}
		in.close();
		out.close();
	}
}
