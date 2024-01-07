import java.util.*;
import java.io.*;

public class clocktree {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("clocktree.in"));
		int n = Integer.parseInt(in.readLine());
		int[] nodes = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			nodes[i] = Integer.parseInt(line.nextToken());
		}
		ArrayList[] edges = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < n - 1; i++) {
			line = new StringTokenizer(in.readLine());
			int node1 = Integer.parseInt(line.nextToken()) - 1;
			int node2 = Integer.parseInt(line.nextToken()) - 1;
			edges[node1].add(node2);
			edges[node2].add(node1);
		}
		in.close();
		int res = 0;
		for(int i = 0; i < n; i++) {
			int[] copynodes = new int[n];
			for(int j = 0; j < n; j++) {
				copynodes[j] = nodes[j];
			}
			int works = startWorks(i, -1, copynodes, edges);
			if(works == 0 || works == 11) {
				res++;
			}
		}
		PrintWriter out = new PrintWriter("clocktree.out");
		out.println(res);
		out.close();
	}
	static int startWorks(int cur, int prev, int[] nodes, ArrayList[] edges) {
		ArrayList<Integer> curchildren = edges[cur];
		if(prev != -1) {
			nodes[cur]++;
		}
		for(int i : curchildren) {
			if(i != prev) {
				nodes[cur] += 12 - startWorks(i, cur, nodes, edges);
				nodes[cur] = ((nodes[cur] - 1) % 12) + 1;
			}
		}
		return nodes[cur] - 1;
	}
}
