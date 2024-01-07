import java.util.*;
import java.io.*;

public class _1605_D {
    static ArrayList<Integer>[] tree;
    static int[] colors, p;
    static int n, count, type, ind;
    static HashSet<Integer> used;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            n = Integer.parseInt(in.readLine());
            tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(line.nextToken()) - 1;
                int v2 = Integer.parseInt(line.nextToken()) - 1;
                tree[v1].add(v2);
                tree[v2].add(v1);
            }
            colors = new int[n];
            Arrays.fill(colors, -1);
            count = 0;
            type = 0;
            dfs(0, 0);
            p = new int[n];
            if(count > n - count) {
                count = n - count;
                type = 1;
            }
            ind = 0;
            used = new HashSet<Integer>();
            int c = 1;
            while(count > 0) {
                if(count % 2 == 1) {
                    fill(c, c * 2 - 1);
                }
                count /= 2;
                c *= 2;
            }
            ind = 0;
            type = 1 - type;
            for(int i = 1; i <= n; i++) {
                if(!used.contains(i)) {
                    while(ind < n) {
                        if(colors[ind] == type) {
                            break;
                        }
                        ind++;
                    }
                    p[ind] = i;
                    ind++;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(p[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static void fill(int start, int end) {
        while(ind < n && start <= end) {
            if(colors[ind] == type) {
                p[ind] = start;
                used.add(start);
                start++;
            }
            ind++;
        }
    }
    static void dfs(int cur, int type) {
        if(colors[cur] != -1) {
            return;
        }
        colors[cur] = type;
        if(type == 0) {
            count++;
        }
        for(int nei : tree[cur]) {
            dfs(nei, 1 - type);
        }
    }
}
