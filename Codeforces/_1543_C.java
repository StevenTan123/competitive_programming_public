import java.util.*;
import java.io.*;

public class _1543_C {
    static double ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            ans = 0;
            StringTokenizer line = new StringTokenizer(in.readLine());
            double[] prob = new double[3];
            for(int i = 0; i < 3; i++) {
                prob[i] = Double.parseDouble(line.nextToken());
            }
            double v = Double.parseDouble(line.nextToken());
            simulate(prob, v, 1, 0);
            out.println(ans);
        }
        in.close();
        out.close();
    }
    static void simulate(double[] prob, double v, double cprob, int count) {
        int alive = 0;
        boolean[] balive = new boolean[3];
        for(int i = 0; i < 3; i++) {
            if(prob[i] > 0.00001) {
                balive[i] = true;
                alive++;
            }
        }
        for(int i = 0; i < 3; i++) {
            if(!balive[i]) {
                continue;
            }
            if(i == 2) {
                ans += (count + 1) * cprob * prob[i];
            }else {
                double dist = prob[i];
                if(prob[i] > v) {
                    dist = v;
                }
                double[] next = new double[3];
                next[i] = prob[i] - dist;
                dist /= alive - 1;
                for(int j = 0; j < 3; j++) {
                    if(balive[j] && j != i) {
                        next[j] = prob[j] + dist;
                    }
                }
                simulate(next, v, cprob * prob[i], count + 1);
            }
        }
    }
}
