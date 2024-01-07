import java.util.*;
import java.io.*;

public class sagehard {
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
		int chepointer = 0;
		int exppointer = ncheap;
		int[] res = new int[n];
		int[] inres = new int[n];
		boolean prevcheap = true;
		int stopi = n;
		for(int i = 0; i < n; i++) {
			if(exppointer < n && prevcheap) {
				exppointer = findNextExp(chepointer, exppointer, spheres);
				if(exppointer >= n) {
					stopi = i;
					break;
				}
				inres[exppointer] = 1;
				res[i] = spheres[exppointer];
				exppointer++;
				prevcheap = false;
			}else if(chepointer < ncheap && !prevcheap) {
				res[i] = spheres[chepointer];
				inres[chepointer] = 1;
				chepointer++;
				prevcheap = true;
			}else {
				stopi = i;
				break;
			}
		}
		int notinp = 0;
		for(int i = stopi; i < n; i++) {
			while(notinp < n && inres[notinp] == 1) {
				notinp++;
			}
			if(notinp < n) {
				res[i] = spheres[notinp];
				notinp++;
			}
		}
		StringBuilder sb = new StringBuilder();
		int cheapcount = 0;
		for(int i = 0; i < n; i++) {
			if(i > 0 && i < n - 1 && res[i] < res[i - 1] && res[i] < res[i + 1]) {
				cheapcount += 1;
			}
			if(i < n - 1) {
				sb.append(res[i]);
				sb.append(" ");
			}else {
				sb.append(res[i]);
			}
		}
		out.println(cheapcount);
		out.println(sb.toString());
		in.close();
		out.close();
	}
	static int findNextExp(int chepointer, int exppointer, int[] spheres) {
		while(exppointer < spheres.length && spheres[chepointer] >= spheres[exppointer]) {
			exppointer++;
		}
		return exppointer;
	}
}
