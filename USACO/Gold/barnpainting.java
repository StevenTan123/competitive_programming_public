import java.io.*;
import java.util.*;

public class barnpainting {
	static long mod = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter out = new PrintWriter("barnpainting.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		ArrayList<Integer>[] tree = new ArrayList[n];
		for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
		for(int i = 0; i < n - 1; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			tree[v1].add(v2);
			tree[v2].add(v1);
		}
		int[] force = new int[n];
		Arrays.fill(force, -1);
		for(int i = 0; i < k; i++) {
			line = new StringTokenizer(in.readLine());
			int b = Integer.parseInt(line.nextToken()) - 1;
			int c = Integer.parseInt(line.nextToken()) - 1;
			force[b] = c;
		}
		long[] res = dfs(0, tree, new boolean[n], force);
		long res2 = 0;
		for(int i = 0; i < 3; i++) {
			res2 += res[i];
			res2 %= mod;
		}
		out.println(res2);
 		in.close();
		out.close();
	}
	static long[] dfs(int node, ArrayList<Integer>[] tree, boolean[] visited, int[] force) {
		visited[node] = true;
		ArrayList<Integer> neighbors = tree[node];
		ArrayList<long[]> cways = new ArrayList<long[]>();
		for(int neighbor : neighbors) {
			if(!visited[neighbor]) {
				cways.add(dfs(neighbor, tree, visited, force));
			}
		}
		if(cways.size() == 0) {
			if(force[node] == -1) {
				return new long[] {1, 1, 1};
			}else {
				long[] curways = new long[3];
				curways[force[node]] = 1;
				return curways;
			}
		}
		if(force[node] == -1) {
			long[] curways = new long[3];
			for(int i = 0; i < 3; i++) {
				long mult = 1;
				for(long[] cway : cways) {
					long cursum = 0;
					for(int j = 0; j < 3; j++) {
						if(j != i) {
							cursum += cway[j];
							cursum %= mod;
						}
					}
					mult *= cursum;
					mult %= mod;
				}
				curways[i] = mult;
			}
			return curways;
		}else {
			long[] curways = new long[3];
			long mult = 1;
			for(long[] cway : cways) {
				long cursum = 0;
				for(int j = 0; j < 3; j++) {
					if(j != force[node]) {
						cursum += cway[j];
						cursum %= mod;
					}
				}
				mult *= cursum;
				mult %= mod;
			}
			curways[force[node]] = mult;
			return curways;
		}
	}
}
