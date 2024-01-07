import java.io.*;
import java.util.*;

public class cownomics {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[][] plain = new int[n][m];
		int[][] spotty = new int[n][m];
		for(int i = 0; i < n; i++) {
			String sline = in.readLine();
			for(int j = 0; j < m; j++) {
				char c = sline.charAt(j);
				if(c == 'A') {
					spotty[i][j] = 0;
				}
				if(c == 'C') {
					spotty[i][j] = 1;
				}
				if(c == 'G') {
					spotty[i][j] = 2;
				}
				if(c == 'T') {
					spotty[i][j] = 3;
				}
			}
		}
		for(int i = 0; i < n; i++) {
			String sline = in.readLine();
			for(int j = 0; j < m; j++) {
				char c = sline.charAt(j);
				if(c == 'A') {
					plain[i][j] = 0;
				}
				if(c == 'C') {
					plain[i][j] = 1;
				}
				if(c == 'G') {
					plain[i][j] = 2;
				}
				if(c == 'T') {
					plain[i][j] = 3;
				}
			}
		}
		in.close();
		int res = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < m; j++) {
				if(j == i) {
					continue;
				}
				for(int k = 0; k < m; k++) {
					if(k == j || k == i) {
						continue;
					}
					boolean norep = true;
					HashSet<Integer> useless = new HashSet<Integer>();
					for(int l = 0; l < n; l++) {
						useless.add(spotty[l][i] * 16 + spotty[l][j] * 4 + spotty[l][k]);
					}
					for(int l = 0; l < n; l++) {
						int hashcode = plain[l][i] * 16 + plain[l][j] * 4 + plain[l][k];
						if(useless.contains(hashcode)) {
							norep = false;
						}
					}
					if(norep) {
						res++;
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("cownomics.out");
		out.println(res / 6);
		out.close();
	}
}
