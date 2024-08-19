import java.util.*;
import java.io.*;

public class alex_expo {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int breaks = 0;
        for (int i = 0; i < k; i++) {
            int l = Integer.parseInt(in.readLine());
            int[] a = new int[l];
            line = new StringTokenizer(in.readLine());
            for (int j = 0; j < l; j++) {
                a[j] = Integer.parseInt(line.nextToken());
            }
            for (int j = 1; j < l; j++) {
                if (a[j] != a[j - 1] + 1) {
                    breaks++;
                }
            }
        }
        out.println(breaks + (k + breaks - m));

		in.close();
		out.close();
	}
}
