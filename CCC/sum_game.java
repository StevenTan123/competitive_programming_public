import java.util.*;
import java.io.*;

public class sum_game {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int N = Integer.parseInt(in.readLine());
        int[] a = new int[N];
        int[] b = new int[N];
        StringTokenizer line1 = new StringTokenizer(in.readLine());
        StringTokenizer line2 = new StringTokenizer(in.readLine());
        int sum1 = 0;
        int sum2 = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(line1.nextToken());
            b[i] = Integer.parseInt(line2.nextToken());
            sum1 += a[i];
            sum2 += b[i];
            if (sum1 == sum2) {
                max = i + 1;
            }
        }
        out.println(max);
        in.close();
        out.close();
    }
}
