import java.util.*;
import java.io.*;

public class second_friend {
    static int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("second_friend_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] grid = new int[r][c];
            for(int i = 0; i < r; i++) {
                String line = in.readLine();
                for(int j = 0; j < c; j++) {
                    if(line.charAt(j) == '^') {
                        grid[i][j] = 1;
                    }else if(line.charAt(j) == '#') {
                        grid[i][j] = 2;
                    }
                }
            }
            boolean[][] dead = new boolean[r][c];
            boolean possible = true;
            ArrayDeque<BFS> queue = new ArrayDeque<BFS>();
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    queue.add(new BFS(i, j));
                }
            }
            while(queue.size() > 0) {
                BFS cur = queue.poll();
                if(!valid(grid, dead, cur.r, cur.c)) {
                    continue;
                }
                int count = 0;
                for(int i = 0; i < 4; i++) {
                    if(!valid(grid, dead, cur.r + dirs[i][0], cur.c + dirs[i][1])) {
                        count++;
                    }
                }
                if(count > 2) {
                    dead[cur.r][cur.c] = true;
                    if(grid[cur.r][cur.c] == 1) {
                        possible = false;
                    }
                    for(int i = 0; i < 4; i++) {
                        queue.add(new BFS(cur.r + dirs[i][0], cur.c + dirs[i][1]));
                    }
                }
            }
            String res = "Case #" + t + ": ";
            if(!possible) {
                out.println(res + "Impossible");
            }else {
                out.println(res + "Possible");
                char[] tokens = new char[] {'.', '^', '#'};
                for(int i = 0; i < r; i++) {
                    StringBuilder sb = new StringBuilder();
                    for(int j = 0; j < c; j++) {
                        if(grid[i][j] == 0 && !dead[i][j]) {
                            sb.append('^');
                        }else {
                            sb.append(tokens[grid[i][j]]);
                        }
                    }
                    out.println(sb.toString());
                }
            }
        }
        in.close();
        out.close();
    }
    //valid if in bounds, not a rock, not dead
    static boolean valid(int[][] grid, boolean[][] dead, int r, int c) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] > 1 || dead[r][c]) {
            return false;
        }
        return true;
    }
    static class BFS {
        int r, c;
        BFS(int rr, int cc) {
            r = rr;
            c = cc;
        }
    }
}
