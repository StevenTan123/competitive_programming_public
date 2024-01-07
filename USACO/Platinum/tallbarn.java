import java.util.*;
import java.io.*;

public class tallbarn {
    static double[] a;
    static int n;
    static long k;
    static double time;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("tallbarn.in"));
        PrintWriter out = new PrintWriter("tallbarn.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        k = Long.parseLong(line.nextToken());
        a = new double[n];
        for(int i = 0; i < n; i++) {
            a[i] = Long.parseLong(in.readLine()) + Math.random() / 1000000;
        }
        double l = 0;
        double r = 1e18;
        while(true) {
            double m = (l + r) / 2;
            long val = valid(m);
            if(val > k) {
                l = m;
            }else if(val < k) {
                r = m;
            }else {
                break;
            }
        }
        out.println((long)(time + 0.5));
        in.close();
        out.close();
    }
    static long valid(double x) {
        long total = 0;
        time = 0;
        for(int i = 0; i < n; i++) {
            long val = find_c(x, a[i]) + 1;
            time += a[i] / val;
            total += val;
        }
        return total;
    }
    static long find_c(double x, double a) {
        double val = (-1 + Math.sqrt(1 + 4 * a / x)) / 2;
        return (long)val;
    }
}
