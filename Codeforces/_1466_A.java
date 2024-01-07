import java.util.*;
import java.io.*;

public class _1466_A {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] trees = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				trees[j] = Integer.parseInt(line.nextToken());
			}
			HashSet<Integer> bases = new HashSet<Integer>();
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					if(trees[k] != trees[j]) {
						bases.add(Math.abs(trees[k] - trees[j]));
					}
				}
			}
			out.println(bases.size());
		}
		in.close();
		out.close();
	}
}
