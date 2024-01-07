import java.util.*;
import java.io.*;

public class _1426_E {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[3];
		int[] b = new int[3];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < 3; i++) {
			a[i] = Integer.parseInt(line.nextToken());
		}
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < 3; i++) {
			b[i] = Integer.parseInt(line.nextToken());
		}
		int max = Math.min(a[0], b[1]) + Math.min(a[1], b[2]) + Math.min(a[2], b[0]);	
		int min = 0;
		int leftover = 0;
		if(a[0] >= b[0] + b[2]) {
			min += b[0] + b[2];
		}else {
			min += a[0];
			leftover = b[0] + b[2] - a[0];
		}
		int scissorsleft = 0;
		int provide = Math.min(b[0], leftover);
		if(provide >= a[1]) {
			min += a[1];
			leftover -= a[1];
			scissorsleft = b[1];
		}else {
			min += provide;
			leftover -= provide;
			int need = a[1] - provide;
			if(b[1] >= need) {
				scissorsleft = b[1] - need;
				min += need;
			}else {
				min += b[1];
			}
		}
		min += Math.min(a[2], scissorsleft + Math.min(leftover, b[2]));
		min = n - min;
		out.println(min + " " + max);
		in.close();
		out.close();
	}
}
