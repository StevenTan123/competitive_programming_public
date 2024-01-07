import java.util.*;
import java.io.*;

public class _1853_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            if (a[0] > 1) {
                out.println(1);
                continue;
            }
            ArrayList<Pair> pairs = new ArrayList<Pair>();
            for (int i = 0; i < n; i++) {
                if (i == n - 1 || a[i + 1] > a[i] + 1) {
                    pairs.add(new Pair(a[i] - i, a[i] + 1));
                }
            }
            long pos = 1;
            int pointer = 0;
            for (int i = 0; i < k; i++) {
                while (pointer < pairs.size() && pairs.get(pointer).smallest <= pos) {
                    pointer++;
                }
                pointer--;
                Pair cur = pairs.get(pointer);
                long next = cur.val + pos - cur.smallest;
                pos = next;
            }
            out.println(pos);
		}
		
        in.close();
		out.close();
	}
    static class Pair {
        int smallest, val;
        Pair(int s, int v) {
            smallest = s;
            val = v;
        }
    }
}
