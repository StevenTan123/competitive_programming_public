import java.util.*;
import java.io.*;

public class mootube_gold {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter("mootube.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int q = Integer.parseInt(line.nextToken());
		PriorityQueue<edge> edges = new PriorityQueue<edge>(new Comparator<edge>() {
			@Override
			public int compare(edge o1, edge o2) {
				return o2.weight - o1.weight;
			}	
		});
		for(int i = 0; i < n - 1; i++) {
			line = new StringTokenizer(in.readLine());
			edges.add(new edge(Integer.parseInt(line.nextToken()) - 1, Integer.parseInt(line.nextToken()) - 1, Integer.parseInt(line.nextToken())));
		}
		int[][] queries = new int[q][4];
		for(int i = 0; i < q; i++) {
			line = new StringTokenizer(in.readLine());
			queries[i][0] = Integer.parseInt(line.nextToken());
			queries[i][1] = Integer.parseInt(line.nextToken()) - 1;
			queries[i][2] = i;
		}
		Arrays.sort(queries, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		int[] parents = new int[n];
		int[] sizes = new int[n];
		for(int i = 0; i < n; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}
		int prevweight = -1;
		int qpointer = 0;
		while(edges.size() > 0) {
			edge cur = edges.poll();
			if(cur.weight != prevweight) {
				while(qpointer < q && queries[qpointer][0] > cur.weight) {
					queries[qpointer][3] = sizes[find(parents, queries[qpointer][1])];
					qpointer++;
				}
			}
			union(parents, sizes, cur.v1, cur.v2);
		}
		while(qpointer < q) {
			queries[qpointer][3] = sizes[find(parents, queries[qpointer][1])];
			qpointer++;
		}
		Arrays.sort(queries, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		for(int i = 0; i < q; i++) {
			out.println(queries[i][3] - 1);
		}
		in.close();
		out.close();
	}
	static int find(int[] parents, int node) {
		if(parents[node] == node) {
			return node;
		}
		parents[node] = find(parents, parents[node]);
		return parents[node];
	}
	static void union(int[] parents, int[] sizes, int a, int b) {
		int roota = find(parents, a);
		int rootb = find(parents, b);
		if(sizes[roota] > sizes[rootb]) {
			parents[rootb] = roota;
			sizes[roota] += sizes[rootb];
		}else {
			parents[roota] = rootb;
			sizes[rootb] += sizes[roota];
		}
	}
	static class edge {
		int v1, v2, weight;
		edge(int vv1, int vv2, int w) {
			v1 = vv1;
			v2 = vv2;
			weight = w;
		}
	}
}
