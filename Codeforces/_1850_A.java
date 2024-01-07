import java.util.*;
import java.io.*;

public class _1850_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());
            out.println(((a + b) >= 10 || (b + c) >= 10 || (a + c) >= 10) ? "YES" : "NO");
		}
		
        in.close();
		out.close();
	}
}
