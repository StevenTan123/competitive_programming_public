import java.util.*;
import java.io.*;

public class _1521_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int min = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if(a[i] < a[min]) min = i;
            }
            if(min == 0) {
                out.println(n - 1);
            }else {
                out.println(n);
            }
            int count = a[min];
            int miss = -1;
            for(int i = 0; i < n; i++) {
                if(i == min) {
                    miss = count;
                    count++;
                    continue;
                }
                out.println((i + 1) + " " + (min + 1) + " " + count + " " + a[min]);
                count++;
            }
            if(min > 0) out.println(1 + " " + (min + 1) + " " + a[min] + " " + miss);
        }
        in.close();
        out.close();
    }
}
