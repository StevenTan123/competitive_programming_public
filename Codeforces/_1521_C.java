import java.util.*;
import java.io.*;

public class _1521_C {
    static int a, b;
    static boolean exit;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] p = new int[n];
            for(int i = 0; i < n - 1; i += 2) {
                solvePair(i, i + 1, n, in);
                if(exit) break;
                p[i] = a;
                p[i + 1] = b;
            }
            if(n % 2 == 1) {
                solvePair(n - 2, n - 1, n, in);
                if(exit) break;
                p[n - 2] = a;
                p[n - 1] = b;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("! ");
            for(int i = 0; i < n; i++) {
                sb.append(p[i]);
                sb.append(' ');
            }
            System.out.println(sb.toString());
            System.out.flush();
        }
        in.close();
    }
    static void solvePair(int i, int j, int n, BufferedReader in) throws IOException {
        int q1 = query(1, i + 1, j + 1, n - 1, in);
        if(exit) return;
        int max = q1;
        if(q1 == n - 1) {
            int q2 = query(1, j + 1, i + 1, n - 1, in);
            if(exit) return;
            max = Math.max(max, q2);
        }
        int q2 = query(1, i + 1, j + 1, max - 1, in);
        if(exit) return;
        if(q2 == max) {
            //j is max
            int q3 = query(2, i + 1, j + 1, 1, in);
            if(exit) return;
            a = q3;
            b = max;
        }else {
            //i is max
            int q3 = query(2, j + 1, i + 1, 1, in);
            if(exit) return;
            a = max;
            b = q3;
        }   
    }
    static int query(int t, int i, int j, int x, BufferedReader in) throws IOException {
        System.out.println("? " + t + " " + i + " " + j + " " + x);
        System.out.flush();
        int ret = Integer.parseInt(in.readLine());
        if(ret == -1) exit = true;
        return ret;
    }
}
