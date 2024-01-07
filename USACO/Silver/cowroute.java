import java.util.*;
import java.io.*;

public class cowroute {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowroute.in"));
		PrintWriter out = new PrintWriter("cowroute.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(line.nextToken()) - 1;
		int b = Integer.parseInt(line.nextToken()) - 1;
		int n = Integer.parseInt(line.nextToken());
		int[][][] graph = new int[1000][1000][2];
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < 1000; j++) {
				graph[i][j][0] = -1;
			}
		}
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			int cost = Integer.parseInt(line.nextToken());
			int num = Integer.parseInt(line.nextToken());
			int[] route = new int[num];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < num; j++) {
				route[j] = Integer.parseInt(line.nextToken()) - 1;
				for(int k = 0; k < j; k++) {
					if(graph[route[k]][route[j]][0] == -1 || cost < graph[route[k]][route[j]][0]) {
						graph[route[k]][route[j]][0] = cost;
						graph[route[k]][route[j]][1] = j - k;
					}else if(cost == graph[route[k]][route[j]][0]) {
						graph[route[k]][route[j]][1] = Math.min(graph[route[k]][route[j]][1], j - k);
					}
				}
			}
		}
		long[] d = new long[1000];
		int[] l = new int[1000];
		dijkstra(graph, d, l, a, b);
		out.println(d[b] + " " + l[b]);
		in.close();
		out.close();
	}
	static void dijkstra(int[][][] graph, long[] d, int[] l, int a, int b) {
		Arrays.fill(d, -1);
		d[a] = 0;
		Arrays.fill(l, -1);
		l[a] = 0;
		boolean[] visited = new boolean[1000];
		for(int i = 0; i < 1000; i++) {
			int min = -1;
			for(int j = 0; j < 1000; j++) {
				if(!visited[j] && d[j] > -1 && (min == -1 || d[j] < d[min])) {
					min = j;
				}
			}
			if(min == -1) {
				break;
			}
			visited[min] = true;
			for(int j = 0; j < 1000; j++) {
				if(graph[min][j][0] > -1) {
					long cost = d[min] + graph[min][j][0];
					int len = l[min] + graph[min][j][1];
					if(d[j] == -1 || cost < d[j]) {
						d[j] = cost;
						l[j] = len;
					}else if(cost == d[j]) {
						l[j] = Math.min(l[j], len);
					}
				}
			}
		}
	}
}
