import java.util.*;
import java.io.*;

public class _1512_F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int[] b = new int[n - 1];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n - 1; i++) {
                b[i] = Integer.parseInt(line.nextToken());
            }
            long min = Long.MAX_VALUE;
            long curdays = 0;
            long balance = a[0];
            for(int i = 0; i < n; i++) {
                curdays++;
                long daysleft = 0;
                if(balance < c) {
                    daysleft = (c - balance) / a[i];
                    if((c - balance) % a[i] != 0) daysleft++;
                }
                min = Math.min(min, curdays + daysleft);
                if(i < n - 1) {
                    if(balance < b[i]) {
                        daysleft = (b[i] - balance) / a[i];
                        if((b[i] - balance) % a[i] != 0) daysleft++;
                        curdays += daysleft;
                        balance = balance + daysleft * a[i] - b[i];
                    }else {
                        balance -= b[i];
                    }
                }
            } 
            out.println(min);
        }
        in.close();
        out.close();
    }
}
