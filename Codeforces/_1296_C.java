import java.util.*;
import java.io.*;

public class _1296_C {
    static final int MAXCOORD = 200005;
    static int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String spath = in.readLine();
            int[] path = new int[n];
            for(int i = 0; i < n; i++) {
                char c = spath.charAt(i);
                if(c == 'U') path[i] = 0;
                else if(c == 'R') path[i] = 1;
                else if(c == 'D') path[i] = 2;
                else path[i] = 3;
            }
            HashMap<Long, Integer> visited = new HashMap<Long, Integer>();
            int x = 0;
            int y = 0;
            int minloop = Integer.MAX_VALUE;
            int l = -1;
            int r = -1;
            for(int i = 0; i <= n; i++) {
                long hash = hash(x, y);
                if(visited.containsKey(hash)) {
                    int looplen = i - visited.get(hash);
                    if(looplen < minloop) {
                        minloop = looplen;
                        l = visited.get(hash);
                        r = i;
                    }
                }
                visited.put(hash, i);
                if(i < n) {
                    x += dirs[path[i]][0];
                    y += dirs[path[i]][1];
                }
            }
            if(minloop == Integer.MAX_VALUE) {
                out.println(-1);
            }else {
                l++;
                out.println(l + " " + r);
            }
        }
        in.close();
        out.close();
    }
    static long hash(int x, int y) {
        return ((long)x) * MAXCOORD + y;
    }
}
