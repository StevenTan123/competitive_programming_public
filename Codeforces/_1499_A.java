import java.util.*;
import java.io.*;

public class _1499_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k1 = Integer.parseInt(line.nextToken());
            int k2 = Integer.parseInt(line.nextToken());
            line = new StringTokenizer(in.readLine());
            int w = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            boolean res = placeable(w, k1, k2) && placeable(b, n - k1, n - k2);
            out.println(res ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
    static boolean placeable(int num, int k1, int k2) {
        int min = Math.min(k1, k2);
        int left = Math.max(k1, k2) - min;
        num -= min;
        if(num <= 0) return true;
        if(num * 2 <= left) return true;
        return false;
    }
}
