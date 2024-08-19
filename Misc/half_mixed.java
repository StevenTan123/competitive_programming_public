import java.util.*;
import java.io.*;

public class half_mixed {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            long nways = (long) n * (n + 1) / 2;
            long mways = (long) m * (m + 1) / 2;
            if (nways % 2 == 0 || mways % 2 == 0) {
                out.println("Yes");
                int len = nways % 2 == 0 ? n : m;
                long left = (nways % 2 == 0 ? (nways / 2) : (mways / 2)) - len;
                ArrayList<Integer> lens = new ArrayList<Integer>();
                int sum = 0;
                for (int l = len; l >= 2; l--) {
                    long lc2 = (long) l * (l - 1) / 2;
                    while (lc2 <= left) {
                        lens.add(l);
                        left -= lc2;
                        sum += l;
                    }
                }
                for (int i = sum; i < len; i++) {
                    lens.add(1);
                }
                int[] strip = new int[len];
                int ind = 0;
                for (int i = 0; i < lens.size(); i++) {
                    for (int j = ind; j < ind + lens.get(i); j++) {
                        strip[j] = i % 2;
                    }
                    ind += lens.get(i);
                }
                int olen = n == len ? m : n;
                int[][] res = new int[olen][len];
                for (int i = 0; i < olen; i++) {
                    res[i] = strip;
                }
                for (int i = 0; i < n; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < m; j++) {
                        if (n == olen) {
                            sb.append(res[i][j]);
                        } else {
                            sb.append(res[j][i]);
                        }
                        sb.append(' ');
                    }
                    out.println(sb.toString());
                }
            } else {
                out.println("No");
            }
		}
		in.close();
		out.close();
	}
}
