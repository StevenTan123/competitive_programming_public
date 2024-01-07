import java.util.*;
import java.io.*;

public class _1837_F {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            int[] rev = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                rev[n - i - 1] = a[i];
            }


        }
		
        in.close();
		out.close();
	}
}
