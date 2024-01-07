import java.util.*;
import java.io.*;

public class _1717_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line1 = new StringTokenizer(in.readLine());
            StringTokenizer line2 = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            int[] b = new int[n];
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line1.nextToken());
                b[i] = Integer.parseInt(line2.nextToken());
                if(a[i] > b[i]) {
                    possible = false;
                }
            }
            for(int i = 0; i < n; i++) {
                if(b[i] > b[(i + 1) % n] + 1) {
                    if(a[i] != b[i]) {
                        possible = false;
                    }
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
