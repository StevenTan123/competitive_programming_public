import java.util.*;
import java.io.*;

public class filter {
    static long[] pow3 = new long[19];
    static Fraction one_third = new Fraction(1, 3);
    static Fraction two_third = new Fraction(2, 3);
    public static void main(String[] args) throws IOException {
        pow3[0] = 1;
        for (int i = 1; i < pow3.length; i++) {
            pow3[i] = pow3[i - 1] * 3;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int N = Integer.parseInt(in.readLine());
        int pow = -1;
        for (int i = 0; i < pow3.length; i++) {
            if (N == pow3[i]) {
                pow = i;
                break;
            }
        }
        if (pow != -1) {
            ArrayList<Long> cantor = new ArrayList<Long>();
            cantor.add(0l);
            cantor.add(1l);
            cantor.add(2l);
            cantor.add(3l);
            for (int i = 1; i < pow; i++) {
                long add = pow3[i] * 2;
                int len = cantor.size();
                for (int j = 0; j < len; j++) {
                    cantor.add(cantor.get(j) + add);
                }
            }
            for (long val : cantor) {
                out.println(val);
            }
        } else {
            for (int i = 0; i <= N; i++) {
                Fraction x = new Fraction(i, N);
                if (is_cantor(x, new TreeSet<Fraction>())) {
                    out.println(i);
                }
            }
        }

        in.close();
        out.close();
    }

    static boolean is_cantor(Fraction x, TreeSet<Fraction> seen) {
        if (x.a == 0 || (x.a == 1 && x.b == 1) || seen.contains(x)) {
            return true;
        }
        seen.add(x);
        if (x.compareTo(one_third) <= 0) {
            Fraction next = new Fraction(x.a * 3, x.b);
            return is_cantor(next, seen);
        } else if (x.compareTo(two_third) < 0) {
            return false;
        } else {
            Fraction next = sub(x, two_third);
            next.a *= 3;
            next.simplify();
            return is_cantor(next, seen);
        }
    }

    static class Fraction implements Comparable<Fraction> {
        long a, b;
        Fraction(long aa, long bb) {
            a = aa;
            b = bb;
            simplify();
        }
        void simplify() {
            long gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
        }
        @Override
        public int compareTo(Fraction o) {
            long ret = a * o.b - o.a * b;
            if (ret < 0) {
                return -1;
            } else if (ret > 0) {
                return 1;
            }
            return 0;
        }
    }

    static Fraction sub(Fraction a, Fraction b) {
        long denom = lcm(a.b, b.b);
        long num = a.a * denom / a.b - b.a * denom / b.b;
        return new Fraction(num, denom);
    }

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
