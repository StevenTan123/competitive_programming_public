import java.util.*;
import java.io.*;

public class _1848_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		
        while(t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            line = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(line.nextToken()) + Integer.parseInt(line.nextToken());
            boolean possible = true;
            for (int i = 0; i < k; i++) {
                line = new StringTokenizer(in.readLine());
                int cur = Integer.parseInt(line.nextToken()) + Integer.parseInt(line.nextToken());
                if (start % 2 == cur % 2) {
                    possible = false;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
		
        in.close();
		out.close();
	}
}
