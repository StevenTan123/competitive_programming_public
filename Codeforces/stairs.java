import java.util.*;
import java.io.*;

public class stairs {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			long x = Long.parseLong(in.readLine());
			long totalsum = 0;
			long n = 1;
			while(totalsum <= x && totalsum >= 0) {
				long nth = (long)Math.pow(2, n) - 1; 
				totalsum += nth * (nth + 1) / 2;
				n++;
			}
			out.println(n - 2);
		}
		in.close();
		out.close();
	}
}
