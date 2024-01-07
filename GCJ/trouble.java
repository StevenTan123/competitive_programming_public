import java.util.*;
import java.io.*;

public class trouble {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int even = n / 2;
            if(n % 2 != 0) even++;
            int odd = n - even;
            int[] evens = new int[even];
            int[] odds = new int[odd];
            for(int i = 0; i < n; i++) {
                if(i % 2 == 0) {
                    evens[i / 2] = Integer.parseInt(line.nextToken());
                }else {
                    odds[(i - 1) / 2] = Integer.parseInt(line.nextToken());
                }
            }
            Arrays.sort(evens);
            Arrays.sort(odds);
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                if(i % 2 == 0) {
                    a[i] = evens[i / 2];
                }else {
                    a[i] = odds[(i - 1) / 2];
                }
            }
            int missort = -1;
            for(int i = 1; i < n; i++) {
                if(a[i] < a[i - 1]) {
                    missort = i - 1;
                    break;
                }
            }
            String res = "Case #" + t + ": ";
            if(missort == -1) {
                res += "OK";
            }else {
                res += missort;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
