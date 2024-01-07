import java.util.*;
import java.io.*;

public class ahaha {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] arr = new int[n];
			int zerocount = 0;
			int onecount = 0;
			for(int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(line.nextToken());
				if(arr[j] == 0) {
					zerocount++;
				}else {
					onecount++;
				}
			}
			if(onecount > zerocount) {
				int k = n / 2;
				if(k % 2 != 0) k++;
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < k - 1; j++) {
					sb.append(1);
					sb.append(" ");
				}
				sb.append(1);
				out.println(k);
				out.println(sb.toString());
			}else {
				int k = n / 2;
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < k - 1; j++) {
					sb.append(0);
					sb.append(" ");
				}
				sb.append(0);
				out.println(k);
				out.println(sb.toString());
			}
		}
		in.close();
		out.close();
	}
}
