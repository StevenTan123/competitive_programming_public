import java.util.*;
import java.io.*;

public class torches {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			long x = Integer.parseInt(line.nextToken());
			long y = Integer.parseInt(line.nextToken());
			long k = Integer.parseInt(line.nextToken());
			long a = k + k * y - 1;
			long b = x - 1;
			long res = a / b;
			if(a % b != 0) {
			    res += 1;
			}
			res += k;
			out.println(res);
		}
		in.close();
		out.close();
	}
}
