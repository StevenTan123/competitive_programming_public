import java.io.*;
import java.util.*;

public class wormsort {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("wormsort.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[] cows = new int[n + 1];
		line = new StringTokenizer(in.readLine());
		ArrayList[] edges = new ArrayList[n + 1];
		boolean alreadySorted = true;
		for(int i = 0; i < n; i++) {
			edges[i + 1] = new ArrayList<edge>();
			cows[i + 1] = Integer.parseInt(line.nextToken());
			if(cows[i + 1] != i + 1) {
				alreadySorted = false;
			}
		}
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(line.nextToken());
			int b = Integer.parseInt(line.nextToken());
			int w = Integer.parseInt(line.nextToken());
			edges[a].add(new edge(b, w));
			edges[b].add(new edge(a, w));
		}
		in.close();
		int lbound = 0;
		int rbound = 1000000001;
		while(lbound < rbound - 1) {
			int middle = (lbound + rbound) / 2;
			if(xWorks(edges, middle, n, cows)) {
				lbound = middle;
			}else {
				rbound = middle;
			}
		}
		PrintWriter out = new PrintWriter("wormsort.out");
		if(alreadySorted) {
			out.println("-1");
		}else {
			out.println(lbound);
		}
		out.close();
	}
	static boolean xWorks(ArrayList[] edges, int x, int n, int[] cows) {
		int[] components = new int[n + 1];
		int curcomp = 1;
		for(int i = 1; i <= n; i++) {
			if(components[i] == 0) {
				genComponents(edges, x, i, components, curcomp);
				curcomp++;
			}
		}
		boolean works = true;
		for(int i = 1; i < components.length; i++) {
			if(components[i] != components[cows[i]]) {
				works = false;
			}
		}
		return works;
	}
	static void genComponents(ArrayList[] edges, int x, int cur, int[] components, int curcomp) {
		if(components[cur] != 0) {
			return;
		}
		components[cur] = curcomp;
		for(Object curedge : edges[cur]) {
			edge cedge = (edge) curedge;
			if(cedge.w >= x) {
				genComponents(edges, x, cedge.o, components, curcomp);
			}
		}
	}
	static class edge {
		int o, w;
		edge(int o, int w){
			this.o = o;
			this.w = w;
		}
	}
}
