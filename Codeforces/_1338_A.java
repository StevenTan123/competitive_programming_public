import java.util.*;
import java.io.*;

public class _1338_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int max = a[0];
            int highestbit = 0;
            for(int i = 1; i < n; i++) {
                if(a[i] < max) {
                    int gap = max - a[i];
                    int count = 0;
                    while(gap > 0) {
                        gap = gap >> 1;
                        count++;
                    }
                    highestbit = Math.max(highestbit, count);
                }
                max = Math.max(max, a[i]);
            }
            out.println(highestbit);
        }
        in.close();
        out.close();
    }
}
