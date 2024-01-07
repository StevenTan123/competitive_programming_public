import java.util.*;
import java.io.*;

public class NestingDepth {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            String s = in.readLine();
            int[] a = new int[s.length()];
            for(int i = 0; i < a.length; i++) {
                a[i] = Character.getNumericValue(s.charAt(i));
            }
            int[][] insert = new int[a.length][2];
            recurse(0, a.length - 1, 0, a, insert);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < a.length; i++) {
                for(int j = 0; j < insert[i][0]; j++) {
                    sb.append('(');
                }
                sb.append(a[i]);
                for(int j = 0; j < insert[i][1]; j++) {
                    sb.append(')');
                }
            }
            out.println("Case #" + t + ": " + sb.toString());
        }
        in.close();
        out.close();
    }
    static void recurse(int l, int r, int sub, int[] a, int[][] insert) {
        if(l > r) return;
        int min = l;
        for(int i = l; i <= r; i++) {
            if(a[i] - sub < a[min] - sub) {
                min = i;
            }
        }
        int newsub = sub + a[min] - sub;
        insert[l][0] += a[min] - sub;
        insert[r][1] += a[min] - sub;
        recurse(l, min - 1, newsub, a, insert);
        recurse(min + 1, r, newsub, a, insert);
    }
}
