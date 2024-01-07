import java.util.*;
import java.io.*;

public class _1875_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            
            line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            int mina = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if (a[i] < a[mina]) {
                    mina = i;
                }
            }
            line = new StringTokenizer(in.readLine());
            int[] b = new int[m];
            int maxb = 0;
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(line.nextToken());
                if (b[i] > b[maxb]) {
                    maxb = i;
                }
            }
            if (a[mina] < b[maxb]) {
                int temp = a[mina];
                a[mina] = b[maxb];
                b[maxb] = temp;
            }

            Arrays.sort(a);
            Arrays.sort(b);
            if ((k - 1) % 2 == 1) {
                int temp = a[n - 1];
                a[n - 1] = b[0];
                b[0] = temp;   
            }
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += a[i];
            }
            out.println(sum);
        }
        
        in.close();
        out.close();
    }
}
