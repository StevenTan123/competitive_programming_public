import java.util.*;
import java.io.*;

public class _1827_B1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());

		while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }

            long res = 0;
            for (int i = 0; i < n; i++) {
                int l1 = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (a[j] < a[i]) {
                        l1 = j + 1;
                        break;
                    }
                }
                int r = n - 1;
                for (int j = i + 1; j < n; j++) {
                    if (a[j] < a[i]) {
                        r = j - 1;
                        break;
                    }
                }
                int l2 = 0;
                for (int j = l1 - 1; j >= 0; j--) {
                    if (a[j] > a[i]) {
                        l2 = j + 1;
                        break;
                    }
                }
                res += (long)(l1 - l2) * (r - i + 1);
            }
            long total = 0;
            for (int i = 0; i < n; i++) {
                total += (long) (i + 1) * (n - i - 1);
            }
            out.println(total - res);
		}

		in.close();
		out.close();
	}
}
