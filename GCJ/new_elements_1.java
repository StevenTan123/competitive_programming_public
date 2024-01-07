import java.util.*;
import java.io.*;

public class new_elements_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[][] mcs = new int[n][2];
            for(int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                mcs[i][0] = Integer.parseInt(line.nextToken());
                mcs[i][1] = Integer.parseInt(line.nextToken());
            }
            TreeSet<Fraction> unique = new TreeSet<Fraction>();
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == j) continue;
                    int dx = mcs[j][0] - mcs[i][0];
                    int dy = mcs[j][1] - mcs[i][1];
                    if(dx == 0 && dy == 0) {
                        possible = false;
                        break;
                    }
                    if(dx >= 0 && dy >= 0 || dx <= 0 && dy <= 0) continue;
                    unique.add(new Fraction(dy, dx));
                }
            }
            String res = "Case #" + t + ": ";
            if(possible) {
                out.println(res + (unique.size() + 1));
            }else {
                out.println(res + 0);
            }
        }
        in.close();
        out.close();
    }
    static class Fraction implements Comparable<Fraction> {
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
        @Override
        public int compareTo(Fraction o) {
            if(a == o.a) return b - o.b;
            return a - o.a;
        }
    }
}
