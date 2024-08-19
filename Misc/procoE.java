import java.util.*;
import java.io.*;

public class procoE {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int x = Integer.parseInt(line.nextToken());
        int y = Integer.parseInt(line.nextToken());
        if(x + y - 1 > n || x * y < n) {
            out.println(-1);
        }else {
            int[] amounts = new int[x - 1];
            Arrays.fill(amounts, 1);
            int left = n - y - x + 1;
            for(int i = 0; i < x - 1; i++) {
                int use = Math.min(left, y - 1);
                left -= use;
                amounts[i] += use;
            }
            int[] res = new int[n];
            for(int i = 0; i < n; i++) {
                res[i] = i + 1;
            }
            reverse(res, 0, y - 1);
            int ind = y;
            for(int i = 0; i < x - 1; i++) {
                reverse(res, ind, ind + amounts[i] - 1);
                ind += amounts[i];
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(res[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static void reverse(int[] a, int l, int r) {
        for(int i = l; i <= (l + r) / 2; i++) {
            int temp = a[i];
            a[i] = a[r - (i - l)];
            a[r - (i - l)] = temp;
        }
    }
}
