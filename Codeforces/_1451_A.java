import java.util.*;
import java.io.*;

public class _1451_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			if(n <= 3) {
				out.println(n - 1);
				continue;
			}
			if(n % 2 == 0) {
				out.println(2);
			}else {
				out.println(3);
			}
		}
		in.close();
		out.close();
	}
}
