import java.util.*;
import java.io.*;

public class _1436_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		ArrayList[] tree = new ArrayList[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
		for(int i = 0; i < n - 1; i++) {
			int pi = Integer.parseInt(line.nextToken()) - 1;
			tree[pi].add(i + 1);
		}
		int[] a = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
		}
		subtree res = dfs(tree, 0, a, new HashSet<Integer>());
		out.println(res.max);
		in.close();
		out.close();
	}
	static subtree dfs(ArrayList[] tree, int curnode, int[] a, HashSet<Integer> visited) {
		visited.add(curnode);
		ArrayList<Integer> neighbors = tree[curnode];
		long subtreemax = 0;
		long subtreesum = 0;
		int numchildren = 0;
		int numleaves = 0;
		for(int i = 0; i < neighbors.size(); i++) {
			int neighbor = neighbors.get(i);
			if(!visited.contains(neighbor)) {
				subtree cur = dfs(tree, neighbor, a, visited);
				subtreemax = Math.max(subtreemax, cur.max);
				subtreesum += cur.sum;
				numchildren++;
				numleaves += cur.numleaves;
			}
		}
		if(numchildren > 0) {
			long gapsfill = numleaves * subtreemax - subtreesum;
			long finalmax = subtreemax;
			if(a[curnode] > gapsfill) {
				long distribute = a[curnode] - gapsfill;
				long each = distribute / numleaves;
				if(distribute % numleaves != 0) each++;
				finalmax += each;
			}
			long finalsum = subtreesum + a[curnode];
			return new subtree(finalsum, finalmax, numleaves);
		}else {
			return new subtree(a[curnode], a[curnode], 1);
		}
	}
	static class subtree {
		long sum, max;
		int numleaves;
		subtree(long s, long m, int nl) {
			sum = s;
			max = m;
			numleaves = nl;
		}
	}
}
