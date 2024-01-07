import java.util.*;
import java.io.*;

public class _1806_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        int[] p = new int[n];
        line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        line = new StringTokenizer(in.readLine());
        for (int i = 1; i < n; i++) {
            p[i] = Integer.parseInt(line.nextToken()) - 1;
        }

        HashMap<Long, Long> seen = new HashMap<Long, Long>();
        for (int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken()) - 1;
            int y = Integer.parseInt(line.nextToken()) - 1;
            Stack<int[]> stack = new Stack<int[]>();
            long sum = (long) a[0] * a[0];
            while (x != p[x]) {
                long hash = hash(x, y);
                if (seen.containsKey(hash)) {
                    sum = seen.get(hash);
                    break;
                }
                stack.push(new int[] {x, y});
                x = p[x];
                y = p[y];
            }
            while (stack.size() > 0) {
                int[] cur = stack.pop();
                sum += (long) a[cur[0]] * a[cur[1]];
                long hash = hash(cur[0], cur[1]);
                seen.put(hash, sum);
            }
            out.println(sum);
        }

        in.close();
        out.close();
    }
    static long hash(int x, int y) {
        return (long) Math.min(x, y) * 100005 + Math.max(x, y);
    }
}
