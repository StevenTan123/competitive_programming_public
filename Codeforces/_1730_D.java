import java.util.*;
import java.io.*;

public class _1730_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s1 = in.readLine();
            String s2 = in.readLine();
            int[][] count = new int[26][26];
            for (int i = 0; i < n; i++) {
                int c1 = (int)(s2.charAt(i)) - 97;
                int c2 = (int)(s1.charAt(n - i - 1)) - 97;
                count[c1][c2]++;
                if (c1 != c2) {
                    count[c2][c1]++;
                }
            }
            boolean possible = true;
            int dupes = 0;
            for (int i = 0; i < 26; i++) {
                if (count[i][i] % 2 == 1) {
                    dupes++;
                }
                for (int j = i + 1; j < 26; j++) {
                    if (count[i][j] % 2 == 1) {
                        possible = false;
                    }
                }
            }
            if (n % 2 == 1 && dupes != 1) {
                possible = false;
            } else if (n % 2 == 0 && dupes > 0) {
                possible = false;
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
