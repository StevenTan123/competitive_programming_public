import java.util.*;
import java.io.*;

public class _1485_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            long res = 0;
            for(int i = 1; i * i <= x; i++) {
                int upper = x / i;
                upper--;
                upper = Math.min(upper, y);
                res += Math.max(0, upper - i);
            }
            out.println(res);
		}
		in.close();
		out.close();
	}
}
