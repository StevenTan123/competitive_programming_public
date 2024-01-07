import java.util.*;
import java.io.*;

public class mootube {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int Q = Integer.parseInt(line.nextToken());
		LinkedList[] edges = new LinkedList[n];
		for(int i = 0; i < n; i++) { 
			edges[i] = new LinkedList<edge>();
		}
		for(int i = 0; i < n - 1; i++) {
			line = new StringTokenizer(in.readLine());
			int p = Integer.parseInt(line.nextToken()) - 1;
			int q = Integer.parseInt(line.nextToken()) - 1;
			int r = Integer.parseInt(line.nextToken());
			edges[p].add(new edge(r, q));
			edges[q].add(new edge(r, p));
		}
		PrintWriter out = new PrintWriter("mootube.out");
		for(int i = 0; i < Q; i++) {
			line = new StringTokenizer(in.readLine());
			int k = Integer.parseInt(line.nextToken());
			int v = Integer.parseInt(line.nextToken()) - 1;
			out.println(countRevelant(v, edges, new boolean[n], k) - 1);
		}
		out.close();
		in.close();
	}
	static int countRevelant(int cur, LinkedList[] edges, boolean[] visited, int k) {
		if(visited[cur]) {
			return 0;
		}
		visited[cur] = true;
		int sum = 1;
		for(Object curedge : edges[cur]) {
			edge cedge = (edge)curedge;
			if(cedge.r >= k) {
				sum += countRevelant(cedge.o, edges, visited, k);
			}
		}
		return sum;
	}
	static class edge {
		int r, o;
		edge(int r, int o) {
			this.r = r;
			this.o = o;
		}
	}
}
