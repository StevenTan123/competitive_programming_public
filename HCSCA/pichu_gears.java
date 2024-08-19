import java.util.*;
import java.io.*;

public class pichu_gears {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(a);
        out.println((double)a[a.length - 1] / a[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(a[i]);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
}
