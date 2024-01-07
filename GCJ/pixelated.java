import java.util.*;
import java.io.*;

public class pixelated {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int r = Integer.parseInt(in.readLine());
            int right = draw_circle_filled(r);
            int[][] a = new int[2 * r + 1][2 * r + 1];
            int wrong = draw_circle_filled_wrong(r, a);
            String res = "Case #" + t + ": ";
            out.println(res + (right - wrong));
        }
        in.close();
        out.close();
    }
    static int round(double val) {
        return (int)Math.ceil(val - 0.5);
    }
    static int draw_circle_filled(int r) {
        int count = 0;
        for(int i = -r; i <= r; i++) {
            for(int j = -r; j <= r; j++) {
                if(round(Math.sqrt(i * i + j * j)) <= r) {
                    count++;
                }
            }
        }
        return count;
    }
    static int draw_circle_filled_wrong(int r, int[][] a) {
        int count = 0;
        for(int i = 0; i <= r; i++) {
            int add = draw_circle_perimeter(i, r, a);
            count += add;
        }
        return count;
    }
    static int draw_circle_perimeter(int ra, int r, int[][] a) {
        int count = 0;
        for(int i = -ra; i <= ra; i++) {
            int j = round(Math.sqrt(ra * ra - i * i));
            count += set_val(i, j, r, a);
            count += set_val(i, -j, r, a);
            count += set_val(j, i, r, a);
            count += set_val(-j, i, r, a);
        }
        return count;
    }
    static int set_val(int i, int j, int r, int[][] a) {
        i += r;
        j += r;
        int ret = 1 - a[i][j];
        a[i][j] = 1;
        return ret;
    }
}
