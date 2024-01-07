import java.util.*;
import java.io.*;

public class _1798_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }

            // legal[i] = true if i...n - 1 form test blocks to the end.
            boolean[] legal = new boolean[n];
            // len_blocks[i] = # blocks formed by tests from i...n - 1, last one can be illegal.
            int[] len_blocks = new int[n];
            // max_increase[i] = max number of new tests creatable by changing one value i...n - 1.
            int[] max_increase = new int[n];
            // max_legal_blocks max number of tests ending legally starting at some index i...n - 1.
            int max_legal_blocks = 0;
            for (int i = n - 1; i >= 0; i--) {
                int next = i + a[i] + 1;
                if (next == n || (next < n && legal[next])) {
                    legal[i] = true;
                }
                len_blocks[i] = (next < n ? len_blocks[next] : 0) + 1;

                max_increase[i] = Math.max(next < n ? max_increase[next] : 0, Math.max(max_legal_blocks + 1 - len_blocks[i], 0));
                
                if (legal[i] && len_blocks[i] > max_legal_blocks) {
                    max_legal_blocks = len_blocks[i];
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n - 1; i++) {
                if (legal[i + 1]) {
                    if (a[i] == len_blocks[i + 1]) {
                        sb.append("0 ");
                    } else {
                        sb.append("1 ");
                    }
                } else {
                    if (len_blocks[i + 1] + max_increase[i + 1] >= a[i]) {
                        sb.append("1 ");
                    } else {
                        sb.append("2 ");
                    }
                }
            }
            out.println(sb.toString());
        }
        
        in.close();
        out.close();
    }
}
