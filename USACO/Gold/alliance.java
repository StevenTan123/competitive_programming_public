import java.io.*;
import java.util.*;

public class alliance {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("alliance.in"));
		PrintWriter out = new PrintWriter("alliance.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] graph = new ArrayList[n];
		int[][] edges = new int[m][2];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			edges[i][0] = v1;
			edges[i][1] = v2;
			graph[v1].add(v2);
			graph[v2].add(v1);
		}
		int[] components = new int[n];
		HashSet<Integer> visited = new HashSet<Integer>();
		int compnum = 1;
		for(int i = 0; i < n; i++) {
			if(!visited.contains(i)) {
				dfs(graph, components, visited, i, compnum);
				compnum++;
			}
		}
		int[] edgecount = new int[compnum - 1];
		for(int i = 0; i < m; i++) {
			int[] curedge = edges[i];
			edgecount[components[curedge[0]] - 1]++;
		}
		int[] vertcount = new int[compnum - 1];
		for(int i = 0; i < n; i++) {
			vertcount[components[i] - 1]++;
		}
		long res = 1;
		long bigboi = 1000000007;
		for(int i = 0; i < compnum - 1; i++) {
			if(vertcount[i] == edgecount[i]) {
				res *= 2;
				res %= bigboi;
			}else if(vertcount[i] == edgecount[i] + 1) {
				res *= vertcount[i];
				res %= bigboi;
			}else {
				res *= 0;
				break;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	static void dfs(ArrayList[] graph, int[] components, HashSet<Integer> visited, int curnode, int compnum) {
		if(visited.contains(curnode)) {
			return;
		}
		visited.add(curnode);
		components[curnode] = compnum;
		ArrayList<Integer> neighbors = graph[curnode];
		for(int i = 0; i < neighbors.size(); i++) {
			dfs(graph, components, visited, neighbors.get(i), compnum);
		}
	}
}
