import java.util.*;
import java.io.*;

public class hilo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] rev = new int[n + 1];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            rev[a[i]] = i;
        }
        int[] prev = new int[n];
        int[] stack = new int[n];
        int p = 0;
        for(int i = n; i > 0; i--) {
            int ind = rev[i];
            while(p > 0 && stack[p - 1] > ind) {
                p--;
            }
            if(p == 0) {
                prev[ind] = -1; 
            }else {
                prev[ind] = stack[p - 1];
            }
            stack[p] = ind;
            p++;
        }
        Arrays.fill(stack, 0);
        p = 0;
        int count = 0;
        out.println(count);
        for(int x = 1; x <= n; x++) {
            int ind = rev[x];
            while(p > 0 && stack[p - 1] > ind) {
                if(prev[stack[p - 1]] != -1 && (p == 1 || prev[stack[p - 2]] != prev[stack[p - 1]])) {
                    count--;
                }
                stack[p - 1] = 0;
                p--;
            }
            stack[p] = ind;
            p++;
            if(prev[ind] != -1 && (p == 1 || prev[stack[p - 2]] != prev[ind])) {
                count++;
            }
            out.println(count);
        }
        in.close();
        out.close();
    }
}
