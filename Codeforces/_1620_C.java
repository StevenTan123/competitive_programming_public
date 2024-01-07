import java.util.*;
import java.io.*;

public class _1620_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            long x = Long.parseLong(line.nextToken());
            String s = in.readLine();
            ArrayList<Integer> digits = new ArrayList<Integer>();
            for(int i = 0; i < n; i++) {
                if(s.charAt(i) == '*') {
                    if(i > 0 && s.charAt(i - 1) == '*') {
                        int ind = digits.size() - 1;
                        digits.set(ind, digits.get(ind) + k);
                    }else {
                        digits.add(k);
                    }
                }
            }
            if(digits.size() == 0) {
                out.println(s);
                continue;
            }
            long[] num = new long[digits.size()];
            num[digits.size() - 1] = x - 1;
            for(int i = digits.size() - 1; i > 0; i--) {
                int cur = digits.get(i) + 1;
                num[i - 1] += num[i] / cur;
                num[i] %= cur;
            }
            StringBuilder sb = new StringBuilder();
            int p = 0;
            for(int i = 0; i < n; i++) {
                if(s.charAt(i) == 'a') {
                    sb.append('a');
                }else {
                    if(i == 0 || s.charAt(i - 1) == 'a') {
                        for(int j = 0; j < num[p]; j++) {
                            sb.append('b');
                        }
                        p++;
                    }
                }
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
