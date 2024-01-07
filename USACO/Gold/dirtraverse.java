import java.util.*;
import java.io.*;

public class dirtraverse {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		ArrayList[] tree = new ArrayList[n];
		String[] names = new String[n];
		for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			names[i] = line.nextToken();
			int m = Integer.parseInt(line.nextToken());
			for(int j = 0; j < m; j++) {
				tree[i].add(Integer.parseInt(line.nextToken()) - 1);
			}
		}
		int[] numleaves = new int[n];
		long sumroot = dfs(tree, 0, 0, names, numleaves);
		long[] dists = new long[n];
		dfs2(tree, 0, sumroot, names, dists, numleaves);
		long res = Long.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			if(tree[i].size() > 0) res = Math.min(res, dists[i]);
		}
		out.println(res);
		in.close();
		out.close();
	}
	@SuppressWarnings("unchecked")
	static long dfs(ArrayList[] tree, int cur, long curlength, String[] names, int[] numleaves) {
		if(tree[cur].size() == 0) {
			numleaves[cur] = 1;
			return curlength - 1;
		}
		long pathssum = 0;
		for(int i = 0; i < tree[cur].size(); i++) {
			int next = (int) tree[cur].get(i);
			long newlength = curlength + names[next].length() + 1;
			pathssum += dfs(tree, next, newlength, names, numleaves);
			numleaves[cur] += numleaves[next];
		}
		return pathssum;
	}
	@SuppressWarnings("unchecked")
	static void dfs2(ArrayList[] tree, int cur, long prevdists, String[] names, long[] dists, int[] numleaves) {
		if(tree[cur].size() == 0) {
			return;
		}
		if(cur == 0) dists[cur] = prevdists;
		else dists[cur] = prevdists - (numleaves[cur] * (names[cur].length() + 1)) + ((numleaves[0] - numleaves[cur]) * 3);
		for(int i = 0; i < tree[cur].size(); i++) {
			int next = (int) tree[cur].get(i);
			dfs2(tree, next, dists[cur], names, dists, numleaves);
		}
	}
}
