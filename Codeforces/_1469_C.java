import java.util.*;
import java.io.*;

public class _1469_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			int[] h = new int[n];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				h[j] = Integer.parseInt(line.nextToken());
			}
			int pheight = h[0];
			boolean possible = true;
			for(int j = 1; j < n - 1; j++) {
				if(h[j] > h[j + 1]) {
					if(Math.abs(h[j] - pheight) <= k - 1) {
						pheight = h[j];
					}else {
						if(h[j] - pheight > k - 1) {
							possible = false;
							break;
						}else {
							pheight = pheight - (k - 1);
							if(pheight - h[j] > k - 1) {
								possible = false;
								break;
							}
						}
					}
				}else {
					int newheight = h[j] + k - 1;
					if(Math.abs(newheight - pheight) <= k - 1) {
						pheight = newheight;
					}else {
						if(pheight > newheight) {
							newheight = pheight - (k - 1);
							if(newheight - h[j] > k - 1) {
								possible = false;
								break;
							}
							pheight = newheight;
						}else {
							newheight = pheight + k - 1;
							if(newheight < h[j]) {
								possible = false;
								break;
							}
							pheight = newheight;
						}
					}
				}
			}
			if(!possible) {
				out.println("NO");
			}else {
				if(Math.abs(h[n - 1] - pheight) <= k - 1) {
					out.println("YES");
				}else {
					out.println("NO");
				}
			}
		}
		in.close();
		out.close();
	}
}
