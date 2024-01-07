import java.util.*;
import java.io.*;

public class _1537_E1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        String s = in.readLine();
        TreeSet<String> sorted = new TreeSet<String>();
        for(int i = 1; i <= n; i++) {
            String prefix = s.substring(0, i);
            StringBuilder cur = new StringBuilder();
            int len = 0;
            while(len < k) {
                len += i;
                cur.append(prefix);
            }
            sorted.add(cur.toString().substring(0, k));
        }
        out.println(sorted.pollFirst());
        in.close();
        out.close();
    }
}
