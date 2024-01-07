import java.util.*;
import java.io.*;

public class _1515_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            int[] w = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                w[i] = Integer.parseInt(line.nextToken());
            }
            int sum = 0;
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                sum += w[i];
                if(sum == x) {
                    if(i == n - 1) {
                        possible = false;
                        break;
                    }
                    sum -= w[i];
                    sum += w[i + 1];
                    swap(w, i, i + 1);
                }
            }
            if(!possible) {
                out.println("NO");
            }else {
                out.println("YES");
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < n; i++) {
                    sb.append(w[i]);
                    if(i < n - 1) sb.append(' ');
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
