import java.util.*;
import java.io.*;

public class _1850_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            int max = -1;
            int val = -1;
            for (int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(line.nextToken());
                int b = Integer.parseInt(line.nextToken());
                if (a <= 10 && b >= val) {
                    max = i + 1;
                    val = b;
                }
            }
            out.println(max);
        }
		
        in.close();
		out.close();
	}
}
