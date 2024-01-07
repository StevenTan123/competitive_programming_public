import java.util.*;
import java.io.*;

public class feeding {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(line.nextToken());
            int K = Integer.parseInt(line.nextToken());
            String s = in.readLine();

            if (K == 0) {
                out.println(N);
                out.println(s);
                continue;
            }
            
            int[] res = new int[N];

            int p = 0;
            boolean end1 = true;
            while (p < N) {
                char c = s.charAt(p);
                if (c == 'G') {
                    if (p + K < N) {
                        res[p + K] = 1;
                    } else {
                        end1 = false;
                    }
                    p += 2 * K;
                }
                p++;
            }

            p = 0;
            boolean end2 = true;
            while (p < N) {
                char c = s.charAt(p);
                if (c == 'H') {
                    if (p + K < N) {
                        res[p + K] = 2;
                    } else {
                        end2 = false;
                    }
                    p += 2 * K;
                }
                p++;
            }

            if (!end1) {
                for (int i = N - 1; i >= 0; i--) {
                    if (res[i] == 0) {
                        res[i] = 1;
                        break;
                    }
                }
            }
            if (!end2) {
                for (int i = N - 1; i >= 0; i--) {
                    if (res[i] == 0) {
                        res[i] = 2;
                        break;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (res[i] == 0) {
                    sb.append('.');
                } else if (res[i] == 1) {
                    sb.append('G');
                    count++;
                } else {
                    sb.append('H');
                    count++;
                }
            }
            out.println(count);
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
