import java.util.*;
import java.io.*;

public class mazetactoe {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        Cell[][] grid = new Cell[n][n];
        int br = 0;
        int bc = 0;
        for(int i = 0; i < n; i++) {
            String line = in.readLine();
            for(int j = 0; j < n; j++) {
                String cur = line.substring(j * 3, j * 3 + 3);
                if(cur.equals("###")) {
                    grid[i][j] = new Cell(0, 0, 0, 0);
                }else if(cur.equals("BBB")) {
                    br = i;
                    bc = j;
                    grid[i][j] = new Cell(1, 0, 0, 0);
                }else if(cur.equals("...")) {
                    grid[i][j] = new Cell(1, 0, 0, 0);
                }else {
                    int type = cur.charAt(0) == 'M' ? 1 : 2;
                    grid[i][j] = new Cell(2, Character.getNumericValue(cur.charAt(1)) - 1, Character.getNumericValue(cur.charAt(2)) - 1, type);
                }
            }
        }
        boolean[][][] visited = new boolean[n][n][19683];
        dfs(br, bc, 0, grid, visited);
        int total = 0;
        for(int i = 0; i < 19683; i++) {
            if(moo(i)) {
                boolean stateseen = false;
                for(int a = 0; a < n; a++) {
                    for(int b = 0; b < n; b++) {
                        if(visited[a][b][i]) {
                            stateseen = true;
                            break;
                        }
                    }
                }
                if(stateseen) {
                    total++;
                }
            }
        }
        out.println(total);
        in.close();
        out.close();
    }
    static boolean moo(int hash) {
        int[][] ttt = dehash(hash);
        for(int i = 0; i < 3; i++) {
            if(mooo(ttt[i][0], ttt[i][1], ttt[i][2]) || mooo(ttt[0][i], ttt[1][i], ttt[2][i])) {
                return true;
            }
        }
        if(mooo(ttt[0][0], ttt[1][1], ttt[2][2]) || mooo(ttt[0][2], ttt[1][1], ttt[2][0])) return true;
        return false;
    }
    static boolean mooo(int a, int b, int c) {
        return (a == 1 && b == 2 && c == 2) || (a == 2 && b == 2 && c == 1);
    }
    static void dfs(int r, int c, int hash, Cell[][] grid, boolean[][][] visited) {
        if(grid[r][c].type == 0) return;
        int nhash = hash;
        if(grid[r][c].type == 2) {
            int[][] ttt = dehash(hash);
            if(ttt[grid[r][c].r][grid[r][c].c] == 0) ttt[grid[r][c].r][grid[r][c].c] = grid[r][c].token;
            nhash = hash(ttt);
        } 
        if(visited[r][c][nhash]) return;
        visited[r][c][nhash] = true;
        if(moo(nhash)) return;
        dfs(r + 1, c, nhash, grid, visited);
        dfs(r - 1, c, nhash, grid, visited);
        dfs(r, c + 1, nhash, grid, visited);
        dfs(r, c - 1, nhash, grid, visited);
    }
    static int[][] dehash(int hash) {
        int[][] res = new int[3][3];
        for(int i = 8; i >= 0; i--) {
            res[i / 3][i % 3] = hash % 3;
            hash /= 3;
        }
        return res;
    }
    static int hash(int[][] ttt) {
        int[] powers = new int[9];
        powers[0] = 1;
        for(int i = 1; i < 9; i++) {
            powers[i] = powers[i - 1] * 3;
        }
        int res = 0;
        int power = 8;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                res += ttt[i][j] * powers[power];
                power--;
            }
        }
        return res;
    }
    static class Cell {
        int type, r, c, token;
        Cell(int t, int rr, int cc, int tt) {
            type = t;
            r = rr;
            c = cc;
            token = tt;
        }
    }
}
