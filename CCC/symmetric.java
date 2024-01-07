import java.util.*;
import java.io.*;

public class symmetric {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int N = Integer.parseInt(in.readLine());
        int[] h = new int[N];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(line.nextToken());
        }

        int[] res = new int[N + 1];
        Arrays.fill(res, Integer.MAX_VALUE);

        // Odd lengths
        for (int i = 0; i < N; i++) {
            int val = 0;
            for (int j = 0; j <= Math.min(i, N - i - 1); j++) {
                int len = j * 2 + 1;
                val += Math.abs(h[i + j] - h[i - j]);
                res[len] = Math.min(res[len], val);
            }
        }

        // Even lengths
        for (int i = 0; i < N - 1; i++) {
            int val = 0;
            for (int j = 0; j <= Math.min(i, N - i - 2); j++) {
                int len = j * 2 + 2;
                val += Math.abs(h[i + j + 1] - h[i - j]);
                res[len] = Math.min(res[len], val);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(res[i]);
            sb.append(' ');
        }
        out.println(sb.toString());

        in.close();
        out.close();
    }
}
