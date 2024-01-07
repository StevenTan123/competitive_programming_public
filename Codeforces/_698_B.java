import java.util.*;
import java.io.*;

public class _698_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		int[] res = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken()) - 1;
			res[i] = a[i];
		}
		int[] components = new int[n];
		int curcomp = 1;
		ArrayList<Integer> reps = new ArrayList<Integer>();
		int head = -1;
		for(int i = 0; i < n; i++) {
			if(components[i] != 0) continue;
			int rep = dfs(a, i, components, curcomp);
			curcomp++;
			if(rep == -1) continue;
			reps.add(rep);
			if(a[rep] == rep) head = rep;
		}
		int count = 0;
		if(head == -1) {
			head = reps.get(0);
			res[reps.get(0)] = reps.get(0); 
			count++;
		}
		for(int i = 0; i < reps.size(); i++) {
			if(reps.get(i) == head) continue;
			res[reps.get(i)] = head;
			count++;
		}
		out.println(count);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(res[i] + 1);
			sb.append(' ');
		}
		out.println(sb.toString());
		in.close();
		out.close();
	}
	static int dfs(int[] a, int node, int[] components, int curcomp) {
		if(components[node] == curcomp) return node;
		if(components[node] != 0) return -1;
		components[node] = curcomp;
		return dfs(a, a[node], components, curcomp);
	}
}
