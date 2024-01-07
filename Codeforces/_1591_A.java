import java.util.*;
import java.io.*;

public class _1591_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int height = 1;
            if(a[0] == 1) {
                height++;
            }
            for(int i = 1; i < n; i++) {
                if(a[i] == 1 && a[i - 1] == 1) {
                    height += 5;
                }else if(a[i] == 1 && a[i - 1] == 0) {
                    height++;
                }else if(a[i] == 0 && a[i - 1] == 0) {
                    height = -1;
                    break;
                }
            }
            out.println(height);
        }
        in.close();
        out.close();
    }
}
