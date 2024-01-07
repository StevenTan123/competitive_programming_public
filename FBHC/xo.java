import java.util.*;
import java.io.*;

public class xo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("xs_and_os_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[][] grid = new int[n][n];
            int[] rows = new int[n];
            int[] cols = new int[n];
            Arrays.fill(rows, 51);
            Arrays.fill(cols, 51);
            for(int i = 0; i < n; i++) {
                int empty = 0;
                int o = 0;
                String line = in.readLine();
                for(int j = 0; j < n; j++) {
                    char c = line.charAt(j);
                    if(c == 'X') {
                        grid[i][j] = 1;
                    }else if(c == 'O') {
                        grid[i][j] = 2;
                        o++;
                    }else {
                        empty++;
                    }
                }
                if(o == 0) {
                    rows[i] = empty;
                }
            }
            for(int i = 0; i < n; i++) {
                int empty = 0;
                int o = 0;
                for(int j = 0; j < n; j++) {
                    if(grid[j][i] == 0) {
                        empty++;
                    }else if(grid[j][i] == 2) {
                        o++;
                    }
                }
                if(o == 0) {
                    cols[i] = empty;
                }
            }
            int overcount = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] == 0 && rows[i] == 1 && cols[j] == 1) {
                        overcount++;
                    }
                }
            }
            int[] need = new int[2 * n];
            for(int i = 0; i < 2 * n; i++) {
                if(i < n) {
                    need[i] = rows[i];
                }else {
                    need[i] = cols[i - n];
                }
            }
            Arrays.sort(need);
            int count = 1;
            for(int i = 1; i < 2 * n; i++) {
                if(need[i] == need[0]) {
                    count++;
                }
            }
            count -= overcount;
            String res = "Case #" + t + ": ";
            if(need[0] == 51) {
                out.println(res + "Impossible");
            }else {
                out.println(res + need[0] + " " + count);
            }
        }
        in.close();
        out.close();
    }
}
