import java.util.*;
import java.io.*;

public class expogo {
    static int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; 
    static char[] chars = new char[] {'S', 'W', 'N', 'E'};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long x = Integer.parseInt(line.nextToken());
            long y = Integer.parseInt(line.nextToken());
            String res = "Case #" + t + ": ";
            if((x + y) % 2 == 0) {
                out.println(res + "IMPOSSIBLE");
                continue;
            }
            long k = log(x, y);
            ArrayDeque<Character> stack = new ArrayDeque<Character>();
            while(k > 0) {
                long pow = (long)Math.pow(2, k - 1);
                for(int i = 0; i < 4; i++) {
                    long newx = x + dirs[i][0] * pow;
                    long newy = y + dirs[i][1] * pow;
                    long log = log(newx, newy);
                    if(log < k) {
                        k--;
                        x = newx;
                        y = newy;
                        stack.push(chars[i]);
                        break;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            while(stack.size() > 0) {
                sb.append(stack.pop());
            }
            out.println(res + sb.toString());
        }
        in.close();
        out.close();
    }
    static long log(long a, long b) {
        long x = Math.abs(a) + Math.abs(b);
        if(x == 0) return 0;
        int k = 1;
        long exp = 2;
        while(exp <= x) {
            k++;
            exp *= 2;
        }
        return k;
    }
}
