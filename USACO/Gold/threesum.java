import java.util.*;
import java.io.*;

public class threesum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("threesum.in"));
		PrintWriter out = new PrintWriter("threesum.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int q = Integer.parseInt(line.nextToken());
		int[] a = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
		}
		//mat[i][j] stores # of 3 SUMS in the range i...j
		long[][] mat = new long[n][n];
		int[] map = new int[2000001];
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				int comp = -a[i] - a[j];
				if(comp >= -1000000 && comp <= 1000000) {
					mat[i][j] += map[comp + 1000000];
				}
				map[a[j] + 1000000]++;
			}
			for(int j = i + 1; j < n; j++) {
				map[a[j] + 1000000]--;
			}
		}
		for(int i = n - 1; i >= 0; i--) {
			for(int j = 0; j < n; j++) {
				mat[i][j] = (i < n - 1 ? mat[i + 1][j] : 0) + (j > 0 ? mat[i][j - 1] : 0)
				+ mat[i][j] - (i < n - 1 && j > 0 ? mat[i + 1][j - 1] : 0);
			}
		}
		for(int i = 0; i < q; i++) {
			line = new StringTokenizer(in.readLine());
			int l = Integer.parseInt(line.nextToken()) - 1;
			int r = Integer.parseInt(line.nextToken()) - 1;
			out.println(mat[l][r]);
		}
		in.close();
		out.close();
	}
}
