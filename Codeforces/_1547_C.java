import java.util.*;
import java.io.*;

public class _1547_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(line.nextToken());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) a[i] = Integer.parseInt(line.nextToken()) - 1;
            line = new StringTokenizer(in.readLine());
            int[] b = new int[m];
            for(int i = 0; i < m; i++) b[i] = Integer.parseInt(line.nextToken()) - 1;
            boolean possible = true;
            int[] res = new int[n + m];
            int ap = 0;
            int bp = 0;
            int len = k;
            while(possible && (ap < n || bp < m)) {
                if(ap < n && a[ap] == -1) {
                    res[ap + bp] = a[ap] + 1;
                    ap++;
                    len++;
                }else if(ap < n && a[ap] < len) {
                    res[ap + bp] = a[ap] + 1;
                    ap++;
                }else if(bp < m && b[bp] == -1) {
                    res[ap + bp] = b[bp] + 1;
                    bp++;
                    len++;
                }else if(bp < m && b[bp] < len) {
                    res[ap + bp] = b[bp] + 1;
                    bp++;
                }else {
                    possible = false;
                }
            }
            if(!possible) {
                out.println(-1);
            }else {
                StringBuilder sb  = new StringBuilder();
                for(int i = 0; i < n + m; i++) {
                    sb.append(res[i]);
                    sb.append(' ');
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
