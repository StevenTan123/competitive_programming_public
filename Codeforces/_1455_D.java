import java.util.*;
import java.io.*;

public class _1455_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int x = Integer.parseInt(line.nextToken());
			int[] a = new int[n];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			int pointer = 0;
			int counter = 0;
			while(!sorted(a)) {
				while(pointer < a.length && a[pointer] <= x) {
					pointer++;
				}
				if(pointer >= a.length) break;
				int temp = x;
				x = a[pointer];
				a[pointer] = temp;
				counter++;
			}
			if(sorted(a)) {
				out.println(counter);
			}else {
				out.println(-1);
			}
		}
		in.close();
		out.close();
	}
	static boolean sorted(int[] a) {
		for(int i = 1; i < a.length; i++) {
			if(a[i] < a[i - 1]) return false;
		}
		return true;
	}
}
