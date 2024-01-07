import java.util.*;
import java.io.*;

public class pascal_walk {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            String res = "Case #" + t + ": ";
            out.println(res);
            if(n <= 30) {
                int r = 1;
                for(int i = 0; i < n; i++) {
                    out.println(r + " " + 1);
                    r++;
                }
                continue;
            }
            int target = n - 30;
            int num0s = 0;
            int r = 1;
            int k = 1;
            while(target > 0) {
                if(target % 2 == 0) {
                    out.println(r + " " + k);
                    num0s++;
                }else {
                    if(k == 1) {
                        for(int i = 1; i <= r; i++) {
                            out.println(r + " " + i);
                        }
                        k = r;
                    }else {
                        for(int i = r; i >= 1; i--) {
                            out.println(r + " " + i);
                        }
                        k = 1;
                    }
                }
                r++;
                if(k > 1) k++;
                target = target >> 1;
            }
            int getback = 30 - num0s;
            for(int i = 0; i < getback; i++) {
                out.println(r + " " + k);
                r++;
                if(k > 1) k++; 
            }
        }
        in.close();
        out.close();
    }
    static long binomial(int n, int k) {
        long num = 1;
        long fact = 1;
        for(int i = 0; i < k; i++) {
            num *= n - k;
            fact *= i + 1;
        }
        return num / fact;
    }
}
