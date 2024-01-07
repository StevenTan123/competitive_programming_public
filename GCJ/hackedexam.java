import java.util.*;
import java.io.*;

public class hackedexam {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int q = Integer.parseInt(line.nextToken());
            String[] a = new String[n];
            int[] s = new int[n];
            int max = 0;
            for(int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                a[i] = line.nextToken();
                s[i] = Integer.parseInt(line.nextToken());
                if(Math.max(s[i], q - s[i]) > Math.max(s[max], q - s[max])) {
                    max = i;
                }
            }
            int opp = q - s[max];
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < q; i++) {
                if(s[max] < opp) {
                    sb.append(a[max].charAt(i) == 'T' ? 'F' : 'T');
                }else {
                    sb.append(a[max].charAt(i));
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + sb.toString() + " " + Math.max(s[max], opp) + "/1");
        }
        in.close();
        out.close();
    }
}
