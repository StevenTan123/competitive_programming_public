import java.util.*;
import java.io.*;

public class nopaint {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int q = Integer.parseInt(line.nextToken());
		String paintstr = in.readLine();
		int[] paint = new int[n];
		for(int i = 0; i < n; i++) {
			paint[i] = (int)paintstr.charAt(i) - 65;
		}
		int[][] queries = new int[q][2];
		for(int i = 0; i < q; i++) {
			line = new StringTokenizer(in.readLine());
			queries[i][0] = Integer.parseInt(line.nextToken()) - 1;
			queries[i][1] = Integer.parseInt(line.nextToken()) - 1;
		}
		int[] revpaint = new int[n];
		for(int i = 0; i < n; i++) revpaint[i] = paint[n - i - 1];
		int[] prefix = new int[n];
		int[] revsuf = new int[n];
		stackpog(prefix, n, paint);
		stackpog(revsuf, n, revpaint);
		int[] suffix = new int[n];
		for(int i = 0; i < n; i++) suffix[i] = revsuf[n - i - 1];
		for(int i = 0; i < q; i++) {
			int pre = queries[i][0] > 0 ? prefix[queries[i][0] - 1] : 0;
			int suf = queries[i][1] < n - 1 ? suffix[queries[i][1] + 1] : 0;
			out.println(pre + suf);
		}
		in.close();
		out.close();
	}
	static void stackpog(int[] arr, int n, int[] paint) {
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		int totalint = 0;
		for(int i = 0; i < n; i++) {
			if(stack.size() == 0 || paint[i] > stack.peek()) {
				stack.push(paint[i]);
				totalint++;
			}else if(paint[i] < stack.peek()) {
				while(stack.size() > 0 && paint[i] < stack.peek()) {
					stack.pop();
				}
				if(stack.size() == 0 || paint[i] > stack.peek()) {
					stack.push(paint[i]);
					totalint++;
				}
			}
			arr[i] = totalint;
		}
	}
}
