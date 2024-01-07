import java.util.*;
import java.io.*;

public class _1437_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] a = new int[n];
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(line.nextToken());
			}
			int curleaves = 1;
			int nextleaves = 0;
			int pointer = 1;
			int level = 1;
			boolean dead = false;
			while(pointer < n) {
				if(curleaves == 0) {
					curleaves = nextleaves;
					nextleaves = 0;
					level++;
					if(dead) {
					    dead = false;
					    nextleaves++;
					}
				}else {
					if(a[pointer] >= a[pointer - 1]) {
						nextleaves++;
					}else {
						curleaves--;
						if(curleaves == 0) {
						    dead = true;
						    continue;
						}
						nextleaves++;
					}
				}
				pointer++;
			}
			out.println(level);
		}
		in.close();
		out.close();
	}
}
