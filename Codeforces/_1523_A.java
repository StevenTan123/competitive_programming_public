import java.util.*;
import java.io.*;

public class _1523_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            String s = in.readLine();
            int[] a = new int[n];
            int first = -1;
            int last = -1;
            for(int i = 0; i < n; i++) {
                a[i] = Character.getNumericValue(s.charAt(i));
                if(a[i] == 1) {
                    last = i;
                    if(first == -1) first = i;
                }
            }
            if(first > -1 && last > -1) {
                int prev = first;
                for(int i = first + 1; i <= last; i++) {
                    if(a[i] == 1) {
                        int gap = i - prev - 1;
                        if(gap % 2 == 1) gap--;
                        gap /= 2;
                        int fill = Math.min(gap, m);
                        for(int j = 0; j < fill; j++) {
                            a[prev + j + 1] = 1;
                            a[i - j - 1] = 1;
                        }
                        prev = i;
                    }
                }
            }
            for(int i = 0; i < Math.min(n, m); i++) {
                int l = first - i - 1;
                if(first > -1 && l >= 0) {
                    a[l] = 1;
                }
                int r = last + i + 1;
                if(last > -1 && r < n) {
                    a[r] = 1;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(a[i]);
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
