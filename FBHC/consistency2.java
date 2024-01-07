import java.util.*;
import java.io.*;

public class consistency2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("consistency_chapter_2_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            String s = in.readLine();
            int[] sarr = new int[s.length()];
            for(int i = 0; i < s.length(); i++) {
                sarr[i] = s.charAt(i) - 65;
            }
            int k = Integer.parseInt(in.readLine());
            int[][] graph = new int[26][26];
            for(int i = 0; i < k; i++) {
                String edge = in.readLine();
                int v1 = edge.charAt(0) - 65;
                int v2 = edge.charAt(1) - 65;
                graph[v1][v2] = 1;
            }
            int[][] sp_mat = new int[26][26];
            for(int i = 0; i < 26; i++) {
                Arrays.fill(sp_mat[i], -1);
                ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
                bfs.add(new BFS(i, 0));
                while(bfs.size() > 0) {
                    BFS cur = bfs.poll();
                    if(sp_mat[i][cur.node] > -1) {
                        continue;
                    }
                    sp_mat[i][cur.node] = cur.dist;
                    for(int j = 0; j < 26; j++) {
                        if(graph[cur.node][j] == 1) {
                            bfs.add(new BFS(j, cur.dist + 1));
                        }
                    }
                }
            }
            int ans = Integer.MAX_VALUE;
            for(int i = 0; i < 26; i++) {
                int cur = 0;
                for(int j = 0; j < s.length(); j++) {
                    if(sp_mat[sarr[j]][i] == -1) {
                        cur = Integer.MAX_VALUE;
                        break;
                    }
                    cur += sp_mat[sarr[j]][i];
                }
                ans = Math.min(ans, cur);
            }
            String res = "Case #" + t + ": ";
            if(ans == Integer.MAX_VALUE) {
                out.println(res + -1);
            }else {
                out.println(res + ans);
            }
        }
        in.close();
        out.close();
    }
    static class BFS {
        int node, dist;
        BFS(int n, int d) {
            node = n;
            dist = d;
        }
    }
}
