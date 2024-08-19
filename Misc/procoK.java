import java.util.*;
import java.io.*;

public class procoK {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        String s = in.readLine();
        int[] pre = new int[k + 1];
        int wc = 0;
        int bc = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == 'W') {
                wc++;
            }else {
                if(bc <= k) pre[bc] = wc;
                bc++;
            }
        }
        int[] suf = new int[k + 1];
        wc = 0;
        bc = 0;
        for(int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == 'W') {
                wc++;
            }else {
                if(bc <= k) suf[bc] = wc;
                bc++;
            }
        }
        int max = 0;
        for(int i = 0; i <= k; i++) {
            max = Math.max(max, pre[i] + suf[k - i]);
        }
        out.println(max);
        in.close();
        out.close();
    }
}
