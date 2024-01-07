import java.util.*;
import java.io.*;

public class _1599_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        double p = Double.parseDouble(line.nextToken());
        int total = choose_three(n);
        for(int i = 0; i <= n; i++) {
            double prob = 0;
            double p1 = (double)i * choose_two(n - i) / total;
            double p2 = (double)choose_two(i) * (n - i) / total;
            double p3 = (double)choose_three(i) / total;
            prob = p1 / 2 + p2 + p3;
            if(prob >= p) {
                out.println(i);
                break;
            }
        }
        in.close();
        out.close();
    }
    static int choose_two(int n) {
        if(n < 2) return 0;
        return n * (n - 1) / 2;
    }
    static int choose_three(int n) {
        if(n < 3) return 0;
        return n * (n - 1) * (n - 2) / 6;
    }
}
