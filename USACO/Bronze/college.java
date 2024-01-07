import java.util.*;
import java.io.*;

public class college {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] c = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(c);
        long max = 0;
        int tuition = -1;
        for (int i = 0; i < n; i++) {
            long cur = (long)(n - i) * c[i];
            if(cur > max) {
                max = cur;
                tuition = c[i];
            }
        }
        out.println(max + " " + tuition);
        in.close();
        out.close();
    }
}
