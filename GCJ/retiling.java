import java.util.*;
import java.io.*;

public class retiling {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            int[][] start = new int[r][c];
            int[][] end = new int[r][c];
            int[][] id = new int[r][c];
            int p1 = 0;
            int p2 = 0;
            for(int i = 0; i < r * 2; i++) {
                String line2 = in.readLine();
                for(int j = 0; j < c; j++) {
                    int val = 0;
                    if(line2.charAt(j) == 'G') val = 1;
                    if(i < r) {
                        start[i][j] = val;
                        if((i + j) % 2 == 0) {
                            id[i][j] = p1;
                            p1++;
                        }else {
                            id[i][j] = p2;
                            p2++;
                        }
                    }else {
                        end[i - r][j] = val;
                    }
                }
            }
            int k = r * c / 2;
            int n = k + (r * c % 2 == 0 ? 0 : 1);
            ArrayList<Integer>[] graph = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int mismatch = 0;
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    if(start[i][j] != end[i][j]) {
                        mismatch++;
                        for(int a = 0; a < 4; a++) {
                            int newi = i + dirs[a][0];
                            int newj = j + dirs[a][1];
                            if(newi >= 0 && newi < r && newj >= 0 && newj < c) {
                                if(start[newi][newj] != start[i][j] && end[newi][newj] != start[newi][newj]) {
                                    int left = id[i][j];
                                    int right = id[newi][newj];
                                    if((i + j) % 2 == 1) {
                                        left = id[newi][newj];
                                        right = id[i][j];
                                    }
                                    graph[left].add(right);
                                }
                            }
                        }
                    }
                }
            }
            int[] mt = new int[k];
            kuhn(n, k, mt, graph);
            for(int i = 0; i < k; i++) {
                if(mt[i] != -1) {
                    mismatch--;
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + mismatch);
        }
        in.close();
        out.close();
    }
    public static boolean try_kuhn(int v, ArrayList<Integer>[] graph, int[] mt, boolean[] used) {
        if(used[v]) {
            return false;
        }
        used[v] = true;
        for(int nei : graph[v]) {
            if(mt[nei] == -1 || try_kuhn(mt[nei], graph, mt, used)) {
                mt[nei] = v;
                return true;
            }
        }
        return false;
    }
    public static void kuhn(int n, int k, int[] mt, ArrayList<Integer>[] graph) {
        Arrays.fill(mt, -1);
        for(int v = 0; v < n; v++) {
            try_kuhn(v, graph, mt, new boolean[n]);
        }
    }
}
