import java.util.*;
import java.io.*;

public class superbull {
	public static void main(String[] args) throws IOException {
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader in = new BufferedReader(new FileReader("superbull.in"));
		PrintWriter out = new PrintWriter("superbull.out");
		int n = Integer.parseInt(in.readLine());
		int[] IDs = new int[n];
		for(int i = 0; i < n; i++) {
			IDs[i] = Integer.parseInt(in.readLine());
		}
		ArrayList<Edge>[] graph = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Edge>();
			for(int j = 0; j < n; j++) {
				if(j != i) {
					graph[i].add(new Edge(j, IDs[i] ^ IDs[j]));
				}
			}
		}
		out.println(MST(graph));
		in.close();
		out.close();
	}
	static long MST(ArrayList<Edge>[] graph) {
		int[] d = new int[graph.length];
		long res = 0;
		for(int i = 0; i < graph.length; i++) {
			int max = 0;
			int ind = -1;
			for(int j = 0; j < graph.length; j++) {
				if(d[j] >= max) {
					max = d[j];
					ind = j;
				}
			}
			res += max;
			d[ind] = -1;
			for(Edge e : graph[ind]) {
				if(d[e.v] != -1) {
					d[e.v] = Math.max(d[e.v], e.w);
				}
			}
		}
		return res;
	}
	static class Edge {
		int v, w;
		Edge(int vv, int ww) {
			v = vv;
			w = ww;
		}
	}
}
