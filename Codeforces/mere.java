import java.util.*;
import java.io.*;

public class mere {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] arr = new int[n];
			int[] sorted = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(line.nextToken());
				sorted[j] = arr[j];
			}
			Arrays.sort(sorted);
			boolean res = true;
			for(int j = 0; j < n; j++) {
				if(arr[j] != sorted[j]) {
					if(arr[j] % sorted[0] != 0) {
						res = false;
						break;
					}
				}
			}
			out.println(res ? "YES" : "NO");
		}
		in.close();
		out.close();
	}
}
