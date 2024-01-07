import java.util.*;
import java.io.*;

public class skyline {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int[] heights = new int[n];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			line.nextToken();
			heights[i] = Integer.parseInt(line.nextToken());
		}
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		int count = 0;
		boolean iszero = false;
		for(int i = 0; i < n; i++) {
			if(heights[i] == 0) iszero = true;
			if(stack.size() > 0 && heights[i] > stack.peek()) {
				stack.push(heights[i]);
				continue;
			}
			while(stack.size() > 0 && heights[i] < stack.peek()) {
				stack.pop();
				count++;
			}
			if(stack.size() == 0 || heights[i] != stack.peek()) {
				stack.push(heights[i]);
			}
		}
		int res = count + stack.size();
		if(iszero) res--;
		out.println(res);
		in.close();
		out.close();
	}
}
