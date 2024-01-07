import java.util.*;
import java.io.*;

public class _1512_G {
    public static void main(String[] args) throws IOException {
        long[] d = new long[10000005];
        for(int i = 1; i < 10000005; i++) {
            for(int j = i; j < 10000005; j += i) {
                d[j] += i;
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int[] first_occ = new int[10000005];
        Arrays.fill(first_occ, -1);
        for(int i = 1; i < 10000005; i++) {
            if(d[i] < 10000005 && first_occ[(int)d[i]] == -1) {
                first_occ[(int)d[i]] = i;
            }
        }
        int t = Integer.parseInt(in.readLine());
        for(int i = 0; i < t; i++) {
            int c = Integer.parseInt(in.readLine());
            out.println(first_occ[c]);
        }
        in.close();
        out.close();
    }
}