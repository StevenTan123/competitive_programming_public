import java.io.*;
import java.util.*;

public class sageeasy {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] spheres = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			spheres[i] = Integer.parseInt(line.nextToken());
		}
		Arrays.sort(spheres);
		int ncheap = 0;
		if(n % 2 == 0) ncheap = n / 2 - 1;
		else ncheap = (int) (n / 2);
		out.println(ncheap);
		int chepointer = 0;
		int exppointer = ncheap;
		int[] res = new int[n];
		boolean prevcheap = true;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			if(prevcheap && exppointer < n) {
				res[i] = spheres[exppointer];
				exppointer++;
				prevcheap = false;
			}else if(!prevcheap && chepointer < ncheap) {
				res[i] = spheres[chepointer];
				chepointer++;
				prevcheap = true;
			}else {
				res[i] = spheres[exppointer];
			}
			if(i < n - 1) {
				sb.append(res[i]);
				sb.append(" ");
			}else {
				sb.append(res[i]);
			}
		}
		out.println(sb.toString());
		in.close();
		out.close();
	}
}
