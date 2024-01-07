import java.util.*;
import java.io.*;

public class _1462_E1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			ArrayList<Integer> a = new ArrayList<Integer>();
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a.add(Integer.parseInt(line.nextToken()));
			}
			Collections.sort(a);
			long res = 0;
			int pointer = 0;
			for(int j = 0; j < n; j++) {
				while(pointer < n && a.get(pointer) <= a.get(j) + 2) {
					pointer++;
				}
				long num = (long)(pointer - 1 - j) * (pointer - 1 - j - 1) / 2;
				res += num;
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
