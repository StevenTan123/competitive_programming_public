import java.util.*;
import java.io.*;

public class fence {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int max = 0;
			for(int j = 0; j < 3; j++) {
				max = Math.max(max, Integer.parseInt(line.nextToken()));
			}
			out.println(max);
		}
		in.close();
		out.close();
	}
}
