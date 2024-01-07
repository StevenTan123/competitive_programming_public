import java.util.*;
import java.io.*;

public class _1516_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            long[][] xors = new long[n][n];
            for(int i = 0; i < n; i++) {
                int xor = 0;
                for(int j = i; j < n; j++) {
                    xor = xor ^ a[j];
                    xors[i][j] = xor;
                }
            }
            boolean possible = false;
            for(int i = 0; i < n - 1; i++) {
                if(xors[0][i] == xors[i + 1][n - 1]) {
                    possible = true;
                    break;
                }
                for(int j = i + 1; j < n - 1; j++) {
                    if(xors[0][i] == xors[i + 1][j] && xors[0][i] == xors[j + 1][n - 1]) {
                        possible = true;
                        break;
                    }
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
