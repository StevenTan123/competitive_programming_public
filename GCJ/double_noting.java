import java.util.*;
import java.io.*;

public class double_noting {
    static final int MAX = 200000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        int[] visited = new int[MAX];
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(line.nextToken(), 2);
            int e = Integer.parseInt(line.nextToken(), 2);
            ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
            bfs.add(new BFS(s, 0));
            int ans = -1;
            while(bfs.size() > 0) {
                BFS cur = bfs.poll();
                if(cur.n == e) {
                    ans = cur.d;
                    break;
                }
                if(cur.n >= MAX || visited[cur.n] == t) continue;
                visited[cur.n] = t;
                String binary = Integer.toBinaryString(cur.n);
                int complement = 0;
                for(int i = 0; i < binary.length(); i++) {
                    int digit = 1 - Character.getNumericValue(binary.charAt(i));
                    complement = complement * 2 + digit;
                }
                bfs.add(new BFS(complement, cur.d + 1));
                bfs.add(new BFS(cur.n * 2, cur.d + 1));
            }
            String res = "Case #" + t + ": ";
            if(ans == -1) {
                out.println(res + "IMPOSSIBLE");
            }else {
                out.println(res + ans);
            }
        }
        in.close();
        out.close();
    }
    static class BFS {
        int n, d;
        BFS(int node, int depth) {
            n = node;
            d = depth;
        }
    }
}
