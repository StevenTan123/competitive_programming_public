import java.util.*;
import java.io.*;

public class _1494_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int[] a = new int[4];
            for(int j = 0; j < 4; j++) {
                a[j] = Integer.parseInt(line.nextToken());
            }
            boolean possible = false;
            for(int j = 0; j < 16; j++) {
                int sub = j;
                int pointer = 3;
                int[] subset = new int[4];
                while(sub > 0) {
                    subset[pointer] = sub & 1;
                    sub = sub >>> 1;
                    pointer--;
                }
                possible = possible || possible(n, a, subset);
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
    static boolean possible(int n, int[] a, int[] subset) {
        for(int i = 0; i < 4; i++) {
            int next = (i + 1) % 4;
            int csum = subset[i] + subset[next];
            if(a[i] < csum || a[i] > csum + n - 2) return false;
        }
        return true;
    }
}
