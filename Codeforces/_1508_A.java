import java.util.*;
import java.io.*;

public class _1508_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String[] a = new String[3];
            for(int i = 0; i < 3; i++) {
                a[i] = in.readLine();
            }
            int[][] c = new int[][] {{0, 1}, {1, 2}, {0, 2}};
            String res = null;
            for(int i = 0; i < 3; i++) {
                res = construct(n, a, c[i]);
                if(res != null) break;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static String construct(int n, String[] a, int[] c) {
        String x = a[c[0]];
        String y = a[c[1]];
        int[] xc = count(x);
        int[] yc = count(y);
        if(xc[0] >= n && yc[0] >= n) {
            StringBuilder sb = new StringBuilder();
            int p1 = 0;
            int p2 = 0;
            while(p1 < 2 * n || p2 < 2 * n) {
                if(p1 >= 2 * n) {
                    sb.append(y.charAt(p2));
                    p2++;
                    continue;
                }else if(p2 >= 2 * n) {
                    sb.append(x.charAt(p1));
                    p1++;
                    continue;
                }
                int dx = Character.getNumericValue(x.charAt(p1));
                int dy = Character.getNumericValue(y.charAt(p2));
                if(dx == 0 && dy == 0) {
                    sb.append('0');
                    p1++;
                    p2++;
                }else if(dx == 1) {
                    sb.append('1');
                    p1++;
                }else {
                    sb.append('1');
                    p2++;
                }
            }
            for(int i = sb.length(); i < 3 * n; i++) {
                sb.append('0');
            }
            return sb.toString();
        }else if(xc[1] >= n && yc[1] >= n) {
            StringBuilder sb = new StringBuilder();
            int p1 = 0;
            int p2 = 0;
            while(p1 < 2 * n || p2 < 2 * n) {
                if(p1 >= 2 * n) {
                    sb.append(y.charAt(p2));
                    p2++;
                    continue;
                }else if (p2 >= 2 * n) {
                    sb.append(x.charAt(p1));
                    p1++;
                    continue;
                }
                int dx = Character.getNumericValue(x.charAt(p1));
                int dy = Character.getNumericValue(y.charAt(p2));
                if(dx == 1 && dy == 1) {
                    sb.append('1');
                    p1++;
                    p2++;
                }else if(dx == 0) {
                    sb.append('0');
                    p1++;
                }else {
                    sb.append('0');
                    p2++;
                }
            }
            for(int i = sb.length(); i < 3 * n; i++) {
                sb.append('0');
            }
            return sb.toString();
        }else {
            return null;
        }
    }
    static int[] count(String s) {
        int[] res = new int[2];
        for(int i = 0; i < s.length(); i++) {
            res[Character.getNumericValue(s.charAt(i))]++;
        }
        return res;
    }
}
