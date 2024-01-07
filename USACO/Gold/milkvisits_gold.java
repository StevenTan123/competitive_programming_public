import java.util.*;
import java.io.*;

public class milkvisits_gold {
	static int prepointer = 0;
	static int postpointer = 0;
	static int[] preorder;
	static int[] postorder;
	static pair[] prevtype;
	static int[] res;
	static HashMap<Integer, ArrayList<query>> queries;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter out = new PrintWriter("milkvisits.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		preorder = new int[n];
		postorder = new int[n];
		prevtype = new pair[n];
		res = new int[m];
		int[] t = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			t[i] = Integer.parseInt(line.nextToken()) - 1;
		}
		ArrayList[] tree = new ArrayList[n];
		for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
		for(int i = 0; i < n - 1; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			tree[v1].add(v2);
			tree[v2].add(v1);
		}
		queries = new HashMap<Integer, ArrayList<query>>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(line.nextToken()) - 1;
			int b = Integer.parseInt(line.nextToken()) - 1;
			int c = Integer.parseInt(line.nextToken()) - 1;
			if(!queries.containsKey(a)) queries.put(a, new ArrayList<query>());
			if(!queries.containsKey(b)) queries.put(b, new ArrayList<query>());
			queries.get(a).add(new query(b, c, i));
			queries.get(b).add(new query(a, c, i));
		}
		dfs(0, tree, new HashSet<Integer>());
		dfs2(0, tree, t, new ArrayList<Integer>(), 0, new HashSet<Integer>());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			sb.append(res[i]);
		}
		out.println(sb.toString());
		in.close();
		out.close();
	}
	static void dfs(int node, ArrayList[] tree, HashSet<Integer> visited) {
		if(visited.contains(node)) return;
		visited.add(node);
		preorder[node] = prepointer;
		prepointer++;
		ArrayList<Integer> nei = tree[node];
		for(int neighbor : nei) {
			dfs(neighbor, tree, visited);
		}
		postorder[node] = postpointer;
		postpointer++;
	}
	static void dfs2(int node, ArrayList[] tree, int[] t, ArrayList<Integer> curpath, int depth, HashSet<Integer> visited) {
		if(visited.contains(node)) return;
		visited.add(node);
		curpath.add(node);
		pair temp = prevtype[t[node]];
		prevtype[t[node]] = new pair(node, depth);
		ArrayList<query> curq = queries.get(node);
		if(curq != null) {
			for(query q : curq) {
				pair prev = prevtype[q.c];
				if(prev == null) continue;
				if(!isAncOf(prev.n, q.b)) {
					res[q.i] = 1;
				}else {
					if(prev.d != depth) {
						if(!isAncOf(curpath.get(prev.d + 1), q.b)) {
							res[q.i] = 1;
						}
					}else {
						res[q.i] = 1;
					}
				}
			}
		}
		ArrayList<Integer> nei = tree[node];
		for(int neighbor : nei) {
			dfs2(neighbor, tree, t, curpath, depth + 1, visited);
		}
		prevtype[t[node]] = temp;
		curpath.remove(curpath.size() - 1);
	}
	static boolean isAncOf(int a, int b) {
		if(preorder[a] <= preorder[b] && postorder[a] >= postorder[b]) return true;
		return false;
	}
	static class query {
		int b, c, i;
		query(int bb, int cc, int ii) {
			b = bb;
			c = cc;
			i = ii;
		}
	}
	static class pair {
		int n, d;
		pair(int node, int depth) {
			n = node;
			d = depth;
		}
	}
}
