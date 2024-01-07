import java.util.*;
import java.io.*;
public class rental {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("rental.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int r = Integer.parseInt(line.nextToken());
		int[] cows = new int[n];
		int[][] sales = new int[m][2];
		int[] rentals = new int[r];
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
		}
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			sales[i][0] = Integer.parseInt(line.nextToken());
			sales[i][1] = Integer.parseInt(line.nextToken());
		}
		for(int i = 0; i < r; i++) {
			rentals[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		Arrays.sort(cows);
		Arrays.sort(sales, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[1], b[1]);
			}
		});
		Arrays.sort(rentals);
		long[] milkcosts = new long[n];
		int salesc = m - 1;
		for(int i = n - 1; i >= 0; i--) {
			if(salesc < 0) {
				break;
			}
			if(i < n - 1) {
				milkcosts[i] = milkcosts[i + 1];
			}
			int leftover = cows[i];
			while(salesc >= 0 && sales[salesc][0] <= leftover) {
				leftover -= sales[salesc][0];
				milkcosts[i] += sales[salesc][0] * sales[salesc][1];
				salesc--;
			}
			if(salesc >= 0) {
				milkcosts[i] += leftover * sales[salesc][1];
				sales[salesc][0] -= leftover;
			}
		}
		long trent = 0;
		long max = 0;
		for(int bk = 0; bk <= n; bk++) {
			if(bk > 0) {
				int rind = r - bk;
				if(rind >= 0) {
					trent += rentals[rind];
				}
			}
			if(bk < n) {
				if(trent + milkcosts[bk] > max) {
					max = trent + milkcosts[bk];
				}
			}else {
				if(trent > max) {
					max = trent;
				}
			}
		}
		PrintWriter out = new PrintWriter("rental.out");
		out.println(max);
		out.close();
	}
}
