import java.util.*;
import java.io.*;

public class balance_tree {
    static int[] max, min;
    public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(line.nextToken());
        int b = Integer.parseInt(line.nextToken());
		while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            max = new int[n];
            min = new int[n];
            ArrayList<Integer>[] tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n - 1; i++) {
                tree[Integer.parseInt(line.nextToken()) - 1].add(i + 1);
            }
            int[][] ranges = new int[n][2];
            int maxL = 0;
            int minR = 1000000000;
            for(int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                ranges[i][0] = Integer.parseInt(line.nextToken());
                ranges[i][1] = Integer.parseInt(line.nextToken());
                maxL = Math.max(maxL, ranges[i][0]);
                minR = Math.min(minR, ranges[i][1]);
            }
            int mid = (maxL + minR) / 2;
            int lower = (maxL - minR) / 2;
            if((maxL - minR) % 2 != 0) {
                lower++;
            }
            out.println(Math.max(dfs(tree, ranges, 0, -1), lower));
            if(b == 1) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < n; i++) {
                    sb.append(Math.max(Math.min(ranges[i][1], mid), ranges[i][0]));
                    if(i != n - 1) {
                        sb.append(' ');
                    }
                }
                out.println(sb.toString());
            }
		}
		in.close();
		out.close();
	}
    static int dfs(ArrayList<Integer>[] tree, int[][] ranges, int node, int prev) {
        min[node] = 1000000000;
        max[node] = 0; 
        int dif = 0;
        for(int nei : tree[node]) {
            if(nei != prev) {
                dif = Math.max(dif, dfs(tree, ranges, nei, node));
                min[node] = Math.min(min[node], min[nei]);
                max[node] = Math.max(max[node], max[nei]);
            }
        }
        dif = Math.max(dif, max[node] - ranges[node][1]);
        dif = Math.max(dif, ranges[node][0] - min[node]);
        max[node] = Math.max(max[node], ranges[node][0]);
        min[node] = Math.min(min[node], ranges[node][1]);
        return dif;
    }
}
