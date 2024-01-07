import java.util.*;
import java.io.*;

public class consecutive_cuts {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("consecutive_cuts_chapter_2_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());

            int[] combine = new int[n * 3 + 1];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                combine[i] = Integer.parseInt(line.nextToken());
            }
            combine[n] = 0;

            boolean equal = true;
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                combine[i + n + 1] = Integer.parseInt(line.nextToken());
                combine[i + 2 * n + 1] = combine[i + n + 1];
                if(combine[i + n + 1] != combine[i]) {
                    equal = false;
                }
            }

            int[] lps = new int[combine.length];
            for(int i = 1; i < combine.length; i++) {
                int j = lps[i - 1];
                while(j > 0 && combine[j] != combine[i]) {
                    j = lps[j - 1];
                }
                if(combine[j] == combine[i]) {
                    lps[i] = j + 1;
                }
            }

            boolean match = false;
            for(int i = n + 1; i < lps.length; i++) {
                if(lps[i] == n) {
                    match = true;
                }
            }

            String res = "Case #" + t + ": ";
            if(n == 2) {
                if(equal && k % 2 == 0 || !equal && k % 2 == 1) {
                    out.println(res + "YES");
                }else {
                    out.println(res + "NO");
                }
            }else {
                if((k == 0 && !equal) || (equal && k == 1) || !match) {
                    out.println(res + "NO");
                }else {
                    out.println(res + "YES");
                }
            }
        }
        in.close();
        out.close();
    }
}
