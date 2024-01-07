import java.util.*;
import java.io.*;

public class _1881_D {
    static final int MAX_VAL = 1000005;
    static int[] spf = new int[MAX_VAL];
    public static void main(String[] args) throws IOException {
        for (int i = 2; i < MAX_VAL; i++) {
            if (spf[i] == 0) {
                for (int j = i; j < MAX_VAL; j += i) {
                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
            
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(line.nextToken());
                while (val > 1) {
                    Integer freq = counts.get(spf[val]);
                    if (freq == null) {
                        freq = 0;
                    }
                    counts.put(spf[val], freq + 1);
                    val /= spf[val];
                }
            }

            boolean possible = true;
            for (int factor : counts.keySet()) {
                if (counts.get(factor) % n != 0) {
                    possible = false;
                    break;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        
        in.close();
        out.close();
    }
}
