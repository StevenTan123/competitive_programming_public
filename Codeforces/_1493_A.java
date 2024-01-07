import java.util.*;
import java.io.*;

public class _1493_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int half = k / 2;
            if(k % 2 != 0) half++;
            int res = n - half;
            StringBuilder sb = new StringBuilder();
            for(int j = half; j <= n; j++) {
                if(j != k) {
                    sb.append(j);
                    sb.append(' ');
                }
            }
            out.println(res);
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
