import java.util.*;
import java.io.*;

public class procoB {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        boolean[][] vert = new boolean[n - 1][n];
        boolean[][] horz = new boolean[n][n - 1];
        int num = 2 * n * (n - 1);
        int[] res = new int[num];
        int[] scores = new int[2];
        int turn = 0;
        for(int i = 0; i < num; i++) {
            res[i] = turn % 2;
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] move = new int[4];
            for(int j = 0; j < 4; j++) {
                move[j] = Integer.parseInt(line.nextToken()) - 1;
            }
            if(move[2] > move[0]) {
                vert[move[0]][move[1]] = true;
            }else {
                horz[move[0]][move[1]] = true;
            }
            int count = closes(move, vert, horz, n);
            if(count > 0) {
                scores[turn % 2] += count;
            }else {
                turn++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < num; i++) {
            if(res[i] == 0) {
                sb.append('A');
            }else {
                sb.append('B');
            }
        }
        out.println(sb.toString());
        out.println(scores[0] + " " + scores[1]);
        in.close();
        out.close();
    }
    static int closes(int[] move, boolean[][] vert, boolean[][] horz, int n) {
        int ret = 0;
        if(move[2] > move[0]) {
            if(move[1] > 0 && full_square(move[0], move[1] - 1, vert, horz)) {
                ret++;
            }
            if(move[1] < n - 1 && full_square(move[0], move[1], vert, horz)) {
                ret++;
            }
        }else {
            if(move[0] > 0 && full_square(move[0] - 1, move[1], vert, horz)) {
                ret++;
            }
            if(move[0] < n - 1 && full_square(move[0], move[1], vert, horz)) {
                ret++;
            }
        }
        return ret;
    }
    static boolean full_square(int r, int c, boolean[][] vert, boolean[][] horz) {
        if(!vert[r][c] || !vert[r][c + 1] || !horz[r][c] || !horz[r + 1][c]) {
            return false;
        }
        return true;
    }  
}
