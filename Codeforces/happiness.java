import java.util.*;
import java.io.*;

public class happiness {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int m = Integer.parseInt(line.nextToken());
			line = new StringTokenizer(in.readLine());
			StringTokenizer line2 = new StringTokenizer(in.readLine());
			int[] living = new int[n];
			int[] happiness = new int[n];
			for(int j = 0; j < n; j++) {
				living[j] = Integer.parseInt(line.nextToken());
				happiness[j] = Integer.parseInt(line2.nextToken());
			}

			ArrayList[] tree = new ArrayList[n];
			for(int j = 0; j < n; j++) tree[j] = new ArrayList<Integer>();
			for(int j = 0; j < n - 1; j++) {
				line = new StringTokenizer(in.readLine());
				int v1 = Integer.parseInt(line.nextToken()) - 1;
				int v2 = Integer.parseInt(line.nextToken()) - 1;
				tree[v1].add(v2);
				tree[v2].add(v1);
			}
			int[] subtreesums = new int[n];
			boolean possible = dfs1(tree, subtreesums, happiness, new HashSet<Integer>(), 0, living);
			out.println(possible ? "YES" : "NO");
		}
		in.close();
		out.close();
	}
	static boolean dfs1(ArrayList[] tree, int[] subtreesums, int[] happiness, HashSet<Integer> visited, int curnode, int[] living) {
		visited.add(curnode);
		ArrayList<Integer> neighbors = tree[curnode];
		int curliving = 0;
		int totalhappy = 0;
		boolean ret = true;
		for(int i = 0; i < neighbors.size(); i++) {
			int curneighbor = neighbors.get(i);
			if(!visited.contains(curneighbor)) {
				ret = ret && dfs1(tree, subtreesums, happiness, visited, curneighbor, living);
				curliving += subtreesums[curneighbor];
				int curhappy = happynum(curneighbor, happiness, subtreesums);
				if(curhappy == -1) return false;
				totalhappy += curhappy;
			}
		}
		subtreesums[curnode] = curliving + living[curnode];
		int parenthappy = happynum(curnode, happiness, subtreesums);
		if(parenthappy == -1) return false;
		if(totalhappy > parenthappy) return false;
		return ret;
	}
	//Given how many people pass through this point and its happiness index, how many people are happy and is it possible
	static int happynum(int node, int[] happiness, int[] subtreesums) {
		if(happiness[node] <= subtreesums[node] && 
				happiness[node] >= -1 * subtreesums[node] && 
				Math.abs(happiness[node] % 2) == Math.abs(subtreesums[node] % 2)) {
			return (subtreesums[node] + happiness[node]) / 2;
		}else {
			return -1;
		}
	}
}
