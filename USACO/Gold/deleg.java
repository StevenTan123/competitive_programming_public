import java.util.*;
import java.io.*;

public class deleg {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("deleg.in"));
		PrintWriter out = new PrintWriter("deleg.out");
		int n = Integer.parseInt(in.readLine());
		ArrayList[] tree = new ArrayList[n];
		for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
		for(int i = 0; i < n - 1; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			tree[v1].add(v2);
			tree[v2].add(v1);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < n; i++) {
			if((n - 1) % i != 0) {
				sb.append(0);
				continue;
			}
			int possible = path_deleg(tree, 0, i, 0, new HashSet<Integer>()) == 0 ? 1 : 0;
			sb.append(possible);
		}
		out.println(sb.toString());
		in.close();
		out.close();
	}
	static int path_deleg(ArrayList[] tree, int first, int k, int node, HashSet<Integer> visited) {
		visited.add(node);
		ArrayList<Integer> nei = tree[node];
		if(nei.size() <= 1 && node != first) return 0;
		HashMap<Integer, Integer> leftfreqs = new HashMap<Integer, Integer>();
		for(int i = 0; i < nei.size(); i++) {
			if(!visited.contains(nei.get(i))) {
				int leftover = path_deleg(tree, first, k, nei.get(i), visited);
				if(leftover == -1) return -1;
				leftover = (leftover + 1) % k;
				if(leftover != 0) {
					Integer val = leftfreqs.get(leftover);
					if(val == null) val = 0;
					leftfreqs.put(leftover, val + 1);
				}
			}
		}
		int numleft = 0;
		int fleft = 0;
		HashSet<Integer> visited2 = new HashSet<Integer>();
		for(int key : leftfreqs.keySet()) {
			if(visited2.contains(key)) continue;
			visited2.add(key);
			visited2.add(k - key);
			if(k - key == key) {
				if(leftfreqs.get(key) % 2 == 1) {
					numleft++;
					fleft = key;
				}
			}else {
				Integer num_counter = leftfreqs.get(k - key);
				if(num_counter == null) num_counter = 0;
				int dif = Math.abs(leftfreqs.get(key) - num_counter);
				numleft += dif;
				if(dif > 0) {
					if(leftfreqs.get(key) > num_counter) {
						fleft = key;
					}else {
						fleft = k - key;
					}
				}
			}
		}
		if(numleft > 1) return -1;
		if(numleft == 0) return 0;
		return fleft;
	}
}
