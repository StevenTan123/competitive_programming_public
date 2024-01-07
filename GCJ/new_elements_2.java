import java.util.*;
import java.io.*;

public class new_elements_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[][] pairs = new int[n][2];
            Fraction lb = null;
            Fraction ub = null;
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                pairs[i][0] = Integer.parseInt(line.nextToken());
                pairs[i][1] = Integer.parseInt(line.nextToken());
                if(i > 0) {
                    int dx = pairs[i][0] - pairs[i - 1][0];
                    int dy = pairs[i][1] - pairs[i - 1][1];
                    if(dx <= 0 && dy <= 0) {
                        possible = false;
                        continue;
                    }
                    if(dx >= 0 && dy >= 0) continue;
                    if(dx < 0) {
                        Fraction nb = new Fraction(dy, -1 * dx);
                        if(ub == null || nb.compareFrac(ub) < 0) {
                            ub = nb;
                        }
                    }else {
                        Fraction nb = new Fraction(-1 * dy, dx);
                        if(lb == null || nb.compareFrac(lb) > 0) {
                            lb = nb;
                        }
                    }
                }
            }
            String res = "Case #" + t + ": ";
            if(!possible) {
                out.println(res + "IMPOSSIBLE");
            }else {
                int x = Integer.MAX_VALUE;
                int y = Integer.MAX_VALUE;
                for(int i = 1; i <= 200; i++) {
                    for(int j = 1; j <= 200; j++) {
                        Fraction cur = new Fraction(i, j);
                        if(ub == null || cur.compareFrac(ub) < 0) {
                            if(lb == null || cur.compareFrac(lb) > 0) {
                                if(i == x) {
                                    y = Math.min(y, j);
                                }else if(i < x) {
                                    x = i;
                                    y = j;
                                }
                            }
                        }
                    }
                }
                if(x == Integer.MAX_VALUE) {
                    out.println(res + "IMPOSSIBLE");
                }else {
                    out.println(res + x + " " + y);
                }
            }
        }
        in.close();
        out.close();
    }
    static class Fraction {
        int a, b;
        Fraction(int aa, int bb) {
            int gcd = gcd(aa, bb);
            a = aa / gcd;
            b = bb / gcd;
        }
        static int gcd(int a, int b) {
            if(b == 0) {
                return a;
            }
            return gcd(b, a % b);
        }
        public int compareFrac(Fraction o) {
            int cross1 = a * o.b;
            int cross2 = b * o.a;
            return cross1 - cross2;
        }
    }
}