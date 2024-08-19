import java.util.*;
import java.io.*;

public class centroids {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			ArrayList[] tree = new ArrayList[n];
			for(int j = 0; j < n; j++) tree[j] = new ArrayList<Integer>();
			for(int j = 0; j < n - 1; j++) {
				StringTokenizer line = new StringTokenizer(in.readLine());
				int v1 = Integer.parseInt(line.nextToken()) - 1;
				int v2 = Integer.parseInt(line.nextToken()) - 1;
				tree[v1].add(v2);
				tree[v2].add(v1);
			}
			int[] subtree_sizes = new int[n];
			dfs(tree, subtree_sizes, 0, new HashSet<Integer>());
			int[] comps = new int[n];
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < n; j++) {
				ArrayList<Integer> edges = tree[j];
				int maxcomp = 0;
				for(int k = 0; k < edges.size(); k++) {
					int node = edges.get(k);
					if(subtree_sizes[node] > subtree_sizes[j]) {
						maxcomp = Math.max(subtree_sizes[0] - subtree_sizes[j], maxcomp);
					}else {
						maxcomp = Math.max(maxcomp, subtree_sizes[node]);
					}
				}
				comps[j] = maxcomp;
				min = Math.min(min, comps[j]);
			}
			int c1 = -1;
			int c2 = -1;
			for(int j = 0; j < n; j++) {
				if(comps[j] == min) {
					if(c1 == -1) {
						c1 = j;
					}else {
						c2 = j;
					}
				}
			}
			if(c2 == -1) {
				String edge = 1 + " " + ((int) tree[0].get(0) + 1);
				out.println(edge);
				out.println(edge);
			}else {
				int[] leafpair = new int[]{-1, -1};
				if(subtree_sizes[c1] > subtree_sizes[c2]) {
					findleaf(tree, subtree_sizes, c2, leafpair);
					leafpair[0] += 1;
					leafpair[1] += 1;
					out.println(leafpair[0] + " " + leafpair[1]);
					out.println(leafpair[0] + " " + (c1 + 1));
				}else {
					findleaf(tree, subtree_sizes, c1, leafpair);
					leafpair[0] += 1;
					leafpair[1] += 1;
					out.println(leafpair[0] + " " + leafpair[1]);
					out.println(leafpair[0] + " " + (c2 + 1));
				}
			}
		}
		in.close();
		out.close();
	}
	static void dfs(ArrayList[] tree, int[] subtree_sizes, int curnode, HashSet<Integer> visited) {
		if(visited.contains(curnode)) {
			return;
		}
		visited.add(curnode);
		ArrayList<Integer> edges = tree[curnode];
		int subtreesum = 1;
		for(int i = 0; i < edges.size(); i++) {
			int node = edges.get(i);
			if(!visited.contains(node)) {
				dfs(tree, subtree_sizes, node, visited);
				subtreesum += subtree_sizes[node];
			}
		}
		subtree_sizes[curnode] = subtreesum;
	}
	static boolean findleaf(ArrayList[] tree, int[] subtree_sizes, int curnode, int[] leafpair) {
		ArrayList<Integer> edges = tree[curnode];
		boolean isleaf = true;
		for(int i = 0; i < edges.size(); i++) {
			int node = edges.get(i);
			if(subtree_sizes[node] < subtree_sizes[curnode]) {
				isleaf = false;
				boolean nextisleaf = findleaf(tree, subtree_sizes, node, leafpair);
				if(nextisleaf) {
					leafpair[1] = curnode;
				}
			}
		}
		if(isleaf) {
			leafpair[0] = curnode;
		}
		return isleaf;
	}
}
