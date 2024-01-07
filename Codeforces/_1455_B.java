import java.util.*;
import java.io.*;

public class _1455_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int x = Integer.parseInt(in.readLine());
			int j = 0;
			int cursum = 0;
			while(cursum < x) {
				j++;
				cursum += j;
			}
			int res = j;
			if(cursum == x + 1) {
				res++;
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
