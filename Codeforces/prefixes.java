import java.util.*;
import java.io.*;

public class prefixes {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] arr = new int[n];
			int[] locked = new int[n];
			for(int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(line.nextToken());
			}
			line = new StringTokenizer(in.readLine());
			ArrayList<Integer> unlocked = new ArrayList<Integer>();
			for(int j = 0; j < n; j++) {
				locked[j] = Integer.parseInt(line.nextToken());
				if(locked[j] == 0) {
					unlocked.add(arr[j]);
				}
			}
			Collections.sort(unlocked);
			int[] res = new int[n];
			int counter = unlocked.size() - 1;
			for(int j = 0; j < n; j++) {
				if(locked[j] == 1) {
					res[j] = arr[j];
				}else {
					if(counter >= 0) {
						res[j] = unlocked.get(counter);
						counter--;
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < n - 1; j++) {
				sb.append(res[j]);
				sb.append(" ");
			}
			sb.append(res[n - 1]);
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
