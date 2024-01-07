import java.util.*;
import java.io.*;

public class cowdate {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowdate.in"));
        PrintWriter out = new PrintWriter("cowdate.out");
        int n = Integer.parseInt(in.readLine());
        double[] p = new double[n];
        for(int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(in.readLine()) / 1e6;
        }
        double max = 0;
        double sum = 0;
        double prod = 1;
        int l = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, sum * prod);
            if(sum < 1) {
                sum += p[i] / (1 - p[i]);
                prod *= 1 - p[i];
            }else {
                sum -= p[l] / (1 - p[l]);
                prod /= 1 - p[l];
                i--;
                l++;
            }
        }
        max = Math.max(max, sum * prod);
        out.println((int)(max * 1e6));
        in.close();
        out.close();
    }
}
