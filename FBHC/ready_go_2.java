import java.util.*;
import java.io.*;

public class ready_go_2 {
    static int count = 0;
    static int[][] dirs = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ready_go_part_2_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int R = Integer.parseInt(line.nextToken());
            int C = Integer.parseInt(line.nextToken());
            int[][] board = new int[R][C];
            for (int i = 0; i < R; i++) {
                String row = in.readLine();
                for (int j = 0; j < C; j++) {
                    board[i][j] = 0;
                    if (row.charAt(j) == 'B') {
                        board[i][j] = 1;
                    } else if (row.charAt(j) == 'W') {
                        board[i][j] = 2;
                    }
                }
            }

            int[][] visited = new int[R][C];
            int[][] visited2 = new int[R][C];
            int[][] fill = new int[R][C];
            int fill_val = 1;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (visited[i][j] == 0 && board[i][j] == 2) {
                        count = 0;
                        int border = dfs1(board, visited, i, j, fill_val);
                        fill_val++;
                        if (border == 1) {
                            dfs2(board, visited2, fill, i, j, count, fill_val);
                        }
                    }
                }
            }

            int max = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    max = Math.max(max, fill[i][j]);
                }
            }

            String res = "Case #" + t + ": ";
            out.println(res + max);
        }
        
        in.close();
        out.close();
    }

    static int dfs1(int[][] board, int[][] visited, int r, int c, int fill) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == 1) {
            return 0;
        }
        if (board[r][c] == 0) {
            if (visited[r][c] == fill) {
                return 0;
            }
            visited[r][c] = fill;
            return 1;
        }
        if (visited[r][c] != 0) {
            return 0;
        }
        count++;
        visited[r][c] = fill; 
        
        return dfs1(board, visited, r + 1, c, fill) + dfs1(board, visited, r - 1, c, fill) + dfs1(board, visited, r, c + 1, fill) + dfs1(board, visited, r, c - 1, fill);
    }

    static void dfs2(int[][] board, int[][] visited, int[][] fill_arr, int r, int c, int val, int fill) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == 1) {
            return;
        }
        if (board[r][c] == 0) {
            if (visited[r][c] == fill) {
                return;
            }
            visited[r][c] = fill;
            fill_arr[r][c] += val;
            return;
        }
        if (visited[r][c] != 0) {
            return;
        }
        visited[r][c] = fill; 
        
        dfs2(board, visited, fill_arr, r + 1, c, val, fill);
        dfs2(board, visited, fill_arr, r - 1, c, val, fill);
        dfs2(board, visited, fill_arr, r, c + 1, val, fill);
        dfs2(board, visited, fill_arr, r, c - 1, val, fill);
    }
}
