import java.util.*;
import java.io.*;

public class saving {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(line.nextToken());
            String ins = line.nextToken();
            int[] a = new int[ins.length()];
            long[] strength = new long[a.length];
            long total = 0;
            long cur = 1;
            int[] count = new int[2];
            for(int i = 0; i < a.length; i++) {
                a[i] = ins.charAt(i) == 'S' ? 1 : 0;
                count[a[i]]++;
                if(a[i] == 0) cur *= 2;
                strength[i] = cur;
                if(a[i] == 1) total += strength[i];
            }
            String res = "Case #" + t + ": ";
            if(count[1] > d) {
                out.println(res + "IMPOSSIBLE");
                continue;
            }
            long sub = total - d;
            if(sub <= 0) {
                out.println(res + 0);
                continue;
            }
            int swaps = 0;
            int nums = 0;
            for(int i = a.length - 1; i >= 0; i--) {
                if(a[i] == 1) nums++;
                if(a[i] == 0) {
                    if(nums > 0) {
                        long ceil = sub / (strength[i] / 2);
                        if(sub % (strength[i] / 2) != 0) ceil++;
                        if(ceil <= nums) {
                            swaps += ceil;
                            break;
                        }else {
                            swaps += nums;
                            sub -= strength[i] / 2 * nums;
                        }
                    }
                }
            }
            out.println(res + swaps);
        }
        in.close();
        out.close();
    }
}
