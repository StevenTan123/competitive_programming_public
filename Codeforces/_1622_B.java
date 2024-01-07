import java.util.*;
import java.io.*;

public class _1622_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] p = new int[n];
            int[] rev = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                p[i] = Integer.parseInt(line.nextToken()) - 1;
                rev[p[i]] = i;
            }
            ArrayList<Integer> zeroes = new ArrayList<Integer>();
            ArrayList<Integer> ones = new ArrayList<Integer>();
            String s = in.readLine();
            int[] sa = new int[n];
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if(c == '0') {
                    zeroes.add(p[i]);
                    sa[i] = 0;
                }else {
                    ones.add(p[i]);
                    sa[i] = 1;
                }
            }
            Collections.sort(zeroes);
            Collections.sort(ones);
            int count = 1;
            int[] res = new int[n];
            for(int zero : zeroes) {
                res[rev[zero]] = count;
                count++;
            }
            for(int one : ones) {
                res[rev[one]] = count;
                count++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(res[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
