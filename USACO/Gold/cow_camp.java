import java.util.*;
import java.io.*;

public class cow_camp {
    static double[] fact;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(line.nextToken()) - 1;
        int k = Integer.parseInt(line.nextToken());
        fact = new double[t + 1];
        for(int i = 1; i <= t; i++) {
            fact[i] = fact[i - 1] + Math.log(i);
        }
        double[] prob = new double[t + 1];
        prob[0] = 1;
        for(int i = 0; i < t; ++i) {
            for(int j = t; j > 0; --j) {
                prob[j] += prob[j - 1];
                prob[j] /= 2;
            }
            prob[0] /= 2;
        }
        double[] p = new double[t + 1];
        for(int i = 0; i < t; i++) {
            p[i] = (i > 0 ? p[i - 1] : 0) + prob[i];
        }
        double s = 0;
        double[] u = new double[t + 1];
        for(int i = t; i >= 0; i--) {
            s += prob[i] * i;
            u[i] = s / (i > 0 ? 1 - p[i - 1] : 1);
        }
        double exp = (double)t / 2;
        int subs = 1;
        while(true) {
            int l = 1;
            int r = 1000000000;
            int jump = -1;
            int floor = (int)exp;
            double x = p[floor];
            double y = floor < t ? u[floor + 1] : 0;
            while(l <= r) {
                int m = (l + r) / 2;
                int newfloor = (int)closed_form(exp, x, y, m);
                if(newfloor > floor) {
                    r = m - 1;
                    jump = m;
                }else {
                    l = m + 1;
                }
            }
            if(subs + jump >= k || jump == -1) {
                exp = closed_form(exp, x, y, k - subs);
                break;
            }else {
                exp = closed_form(exp, x, y, jump);
                subs += jump;
            }
        }
        out.println(exp + 1);
        in.close();
        out.close();
    }
    static double closed_form(double start, double x, double y, int iters) {
        double pow = Math.pow(x, iters);
        return pow * start + (1 - pow) * y;
    }
}
