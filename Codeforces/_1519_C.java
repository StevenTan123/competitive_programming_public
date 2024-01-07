import java.util.*;
import java.io.*;

public class _1519_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            ArrayList<Integer>[] ppl = new ArrayList[n];
            for(int i = 0; i < n; i++) ppl[i] = new ArrayList<Integer>();
            StringTokenizer line = new StringTokenizer(in.readLine());
            StringTokenizer line2 = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                int u = Integer.parseInt(line.nextToken()) - 1;
                int s = Integer.parseInt(line2.nextToken());
                ppl[u].add(s);
            }
            long[] res = new long[n + 1];
            for(int i = 0; i < n; i++) {
                ArrayList<Integer> uni = ppl[i];
                Collections.sort(uni);
                long[] pre = new long[uni.size()];
                for(int j = uni.size() - 1; j >= 0; j--) {
                    int pind = uni.size() - j - 1;
                    pre[pind] = (pind > 0 ? pre[pind - 1] : 0) + uni.get(j);
                }
                for(int j = 1; j <= uni.size(); j++) {
                    int used = uni.size() - (uni.size() % j) - 1;
                    res[j] += pre[used];
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i <= n; i++) {
                sb.append(res[i]);
                if(i < n) sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}