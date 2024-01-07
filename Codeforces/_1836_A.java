import java.util.*;
import java.io.*;

public class _1836_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            int[] count = new int[105];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                count[Integer.parseInt(line.nextToken())]++;
            }
            boolean possible = true;
            for (int i = 1; i < count.length; i++) {
                if (count[i] > count[i - 1]) {
                    possible = false;
                    break;
                }
            }
            out.println(possible ? "YES" : "NO");
		}

		in.close();
		out.close();
	}
}
