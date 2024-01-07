import java.util.*;
import java.io.*;

public class _1153_D {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        line = new StringTokenizer(in.readLine());
        for(int i = 1; i < n; i++) {
            tree[Integer.parseInt(line.nextToken()) - 1].add(i);
        }
        int val = dfs(tree, a, 0);
        out.println(count - val + 1);
        in.close();
        out.close();
    }
    static int dfs(ArrayList<Integer>[] tree, int[] a, int cur) {
        if(tree[cur].size() == 0) {
            count++;
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int nei : tree[cur]) {
            int val = dfs(tree, a, nei);
            sum += val;
            min = Math.min(min, val);
        }
        if(a[cur] == 0) {
            return sum;
        }else {
            return min;
        }
    }
}
