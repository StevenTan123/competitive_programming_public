import java.util.*;
import java.io.*;

public class security_update {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int c = Integer.parseInt(line.nextToken());
            int d = Integer.parseInt(line.nextToken());
            int[][] x = new int[c][2];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < c - 1; i++) {
                x[i + 1][0] = Integer.parseInt(line.nextToken()) * -1;
                x[i + 1][1] = i + 1;
            }
            int[][][] graph = new int[c][c][2];
            for(int i = 0; i < d; i++) {
                line = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(line.nextToken()) - 1;
                int v2 = Integer.parseInt(line.nextToken()) - 1;
                graph[v1][v2][0] = 1;
                graph[v2][v1][0] = 1;
                graph[v1][v2][1] = i;
                graph[v2][v1][1] = i;
            }
            Arrays.sort(x, new Comparator<int[]>() {
                @Override
                public int compare(int[] a1, int[] a2) {
                    return a1[0] - a2[0];
                }
            });
            int[] ans = new int[d];
            int[] dist = new int[c];
            int curdist = 0;
            for(int i = 1; i < c; i++) {
                if(x[i][0] != x[i - 1][0]) {
                    curdist++;
                }
                dist[i] = curdist;
                for(int j = 0; j < i; j++) {
                    int v1 = x[i][1];
                    int v2 = x[j][1];
                    if(graph[v1][v2][0] == 1) {
                        ans[graph[v1][v2][1]] = Math.max(1, curdist - dist[j]);
                    }
                }
            }
            String res = "Case #" + t + ": ";
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < d; i++) {
                sb.append(ans[i]);
                if(i < d - 1) sb.append(' ');
            }
            out.println(res + sb.toString());
        }
        in.close();
        out.close();
    }
}
