import java.util.*;
import java.io.*;

public class pancake {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            String s = line.nextToken();
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[s.length()];
            for(int i = 0; i < a.length; i++) a[i] = s.charAt(i) == '+' ? 1 : 0;
            boolean[] flipped = new boolean[a.length];
            int flipk = 0;
            int total = 0;
            for(int i = 0; i <= a.length - k; i++) {
                int cur = a[i];
                if(flipk % 2 == 1) cur = 1 - cur;
                if(cur == 0) {
                    total++;
                    flipped[i] = true;
                    flipk++;
                }
                if(i >= k - 1 && flipped[i - k + 1]) flipk--;
            }
            boolean possible = true;
            for(int i = a.length - k + 1; i < a.length; i++) {
                int cur = a[i];
                if(flipk % 2 == 1) cur = 1 - cur;
                if(cur == 0) {
                    possible = false;
                    break;
                }
                if(i >= k - 1 && flipped[i - k + 1]) flipk--;
            }
            String res = "Case #" + t + ": ";
            if(possible) {
                out.println(res + total);
            }else {
                out.println(res + "IMPOSSIBLE");
            }
        }
        in.close();
        out.close();
    }
}
