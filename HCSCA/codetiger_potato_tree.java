import java.util.*;
import java.io.*;

public class codetiger_potato_tree {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int n = Integer.parseInt(in.readLine());
		StringTokenizer line = new StringTokenizer(in.readLine());
        int[] m = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            m[i] = Integer.parseInt(line.nextToken());
            sum += m[i];
        }

        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            tree[u].add(v);
            tree[v].add(u);
        }

        out.println(dfs(tree, m, 0, -1) - sum);

		in.close();
		out.close();
	}
    static long dfs(ArrayList<Integer>[] tree, int[] m, int cur, int prev) {
        long max = 0;
        int count = 0;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                max = Math.max(max, dfs(tree, m, nei, cur));
                count++;
            }
        }
        return max * count + m[cur];
    }
}
