import java.util.*;
import java.io.*;

public class _1490_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            ArrayList<Integer>[] tree = new ArrayList[n];
            for(int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(line.nextToken()) - 1;
                tree[j] = new ArrayList<Integer>();
            }
            int start = build_tree(tree, 0, n - 1, a);
            int[] d = new int[n];
            Arrays.fill(d, -1);
            dfs(tree, start, 0, d);
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                sb.append(d[j]);
                sb.append(' ');
            }
            out.println(sb.toString());
		}
		in.close();
		out.close();
	}
    static void dfs(ArrayList<Integer>[] tree, int node, int depth, int[] d) {
        if(d[node] > -1) return;
        d[node] = depth;
        ArrayList<Integer> neighbors = tree[node];
        for(int neighbor : neighbors) {
            dfs(tree, neighbor, depth + 1, d);
        }
    }
    static int build_tree(ArrayList<Integer>[] tree, int l, int r, int[] a) {
        if(l > r) return -1;
        int max = l;
        for(int i = l + 1; i <= r; i++) {
            if(a[i] >= a[max]) max = i;
        }
        int left = build_tree(tree, l, max - 1, a);
        int right = build_tree(tree, max + 1, r, a);
        if(left > -1) tree[max].add(left);
        if(right > -1) tree[max].add(right);
        return max;
    }
}
