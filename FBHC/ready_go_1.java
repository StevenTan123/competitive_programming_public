import java.util.*;
import java.io.*;

public class ready_go_1 {
    static int[][] dirs = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ready_go_part_1_input.txt"));
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

            boolean possible = false;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        
                        for (int k = 0; k < 4; k++) {
                            int row = i + dirs[k][0];
                            int col = j + dirs[k][1];
                            if (row >= 0 && row < R && col >= 0 && col < C && board[row][col] == 2) {
                                if (!dfs(board, new boolean[R][C], row, col)) {
                                    possible = true;
                                    break;
                                }
                            }
                        }
                        if (possible) {
                            break;
                        }

                        board[i][j] = 0;
                    }
                }
            }

            String res = "Case #" + t + ": ";
            out.println(res + (possible ? "YES" : "NO"));
        }
        
        in.close();
        out.close();
    }

    static boolean dfs(int[][] board, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == 1) {
            return false;
        }
        if (board[r][c] == 0) {
            return true;
        }
        if (visited[r][c]) {
            return false;
        }
        visited[r][c] = true; 
        
        return dfs(board, visited, r + 1, c) || dfs(board, visited, r - 1, c) || dfs(board, visited, r, c + 1) || dfs(board, visited, r, c - 1);
    }
}
