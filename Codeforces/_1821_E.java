import java.util.*;
import java.io.*;

public class _1821_E {
    static long cost = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int k = Integer.parseInt(in.readLine());
            String rbs = in.readLine();
            String cur = rbs;
            for (int i = 0; i <= k; i++) {
                cur = remove_largest(cur);
            }
            out.println(cost);
        }

        in.close();
        out.close();
    }
    static String remove_largest(String rbs) {
        cost = 0;
        int max = 0;
        int l = -1;
        int r = -1;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < rbs.length(); i++) {
            if (rbs.charAt(i) == ')') {
                int prev = stack.pop();
                cost += (i - prev - 1) / 2;
                if (i - prev > max) {
                    max = i - prev;
                    l = prev;
                    r = i;
                }
            } else {
                stack.push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rbs.length(); i++) {
            if (i != l && i != r) {
                sb.append(rbs.charAt(i));
            }
        }
        return sb.toString();
    }
}
