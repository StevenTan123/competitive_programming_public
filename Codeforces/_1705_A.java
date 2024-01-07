import java.util.*;
import java.io.*;

public class _1705_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            int[] h = new int[2 * n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < 2 * n; i++) {
                h[i] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(h);
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                if(h[i] + x > h[i + n]) {
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
