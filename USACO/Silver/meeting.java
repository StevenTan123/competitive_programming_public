import java.util.*;
import java.io.*;

public class meeting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("meeting.in"));
        PrintWriter out = new PrintWriter("meeting.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][][] dag = new int[2][n][n];
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            int c = Integer.parseInt(line.nextToken());
            int d = Integer.parseInt(line.nextToken());
            dag[0][a][b] = c;
            dag[1][a][b] = d;
        }
        boolean[] v1 = bfs(dag[0], n);
        boolean[] v2 = bfs(dag[1], n);
        int res = -1;
        for(int i = 0; i < 10005; i++) {
            if(v1[i] && v2[i]) {
                res = i;
                break;
            }
        }
        if(res == -1) {
            out.println("IMPOSSIBLE");
        }else {
            out.println(res);
        }
        in.close();
        out.close();
    }
    static boolean[] bfs(int[][] dag, int n) {
        boolean[][] visited = new boolean[n][10005];
        ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
        bfs.add(new BFS(0, 0));
        while(bfs.size() > 0) {
            BFS cur = bfs.poll();
            if(visited[cur.node][cur.sum]) continue;
            visited[cur.node][cur.sum] = true;
            for(int i = 0; i < n; i++) {
                if(dag[cur.node][i] > 0) {
                    bfs.add(new BFS(i, cur.sum + dag[cur.node][i]));
                }
            }
        }
        return visited[n - 1];
    }
    static class BFS {
        int node, sum;
        BFS(int n, int s) {
            node = n;
            sum = s;
        }
    }
}
