import java.util.*;
import java.io.*;

public class _1836_C {
    static int[] pow10;
	public static void main(String[] args) throws IOException {
        pow10 = new int[8];
        pow10[0] = 1;
        for (int i = 1; i < pow10.length; i++) {
            pow10[i] = pow10[i - 1] * 10;
        }
        
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(line.nextToken());
            int B = Integer.parseInt(line.nextToken());
            int C = Integer.parseInt(line.nextToken());
            long k = Long.parseLong(line.nextToken());

            int resa = -1;
            int resb = -1;
            for (int a = pow10[A - 1]; a < pow10[A]; a++) {
                int min = Math.max(pow10[B - 1], pow10[C - 1] - a);
                int max = Math.min(pow10[C] - a - 1, pow10[B] - 1);
                if (max >= min) {
                    int sub = max - min + 1;
                    if (sub >= k) {
                        resa = a;
                        resb = (int) (min + k - 1);
                        break;
                    } else {
                        k -= sub;
                    }
                }
            }
            if (resa == -1) {
                out.println(-1);
            } else {
                out.println(resa + " + " + resb + " = " + (resa + resb));
            }
		}

		in.close();
		out.close();
	}
}
