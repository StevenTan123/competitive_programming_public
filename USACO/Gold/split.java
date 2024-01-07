import java.util.*;
import java.io.*;

public class split {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("split.in"));
		PrintWriter out = new PrintWriter("split.out");
		int n = Integer.parseInt(in.readLine());
		long[][] cowsx = new long[n][2];
		long[][] cowsy = new long[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			cowsx[i][0] = Integer.parseInt(line.nextToken());
			cowsx[i][1] = Integer.parseInt(line.nextToken());
			cowsy[i][0] = cowsx[i][0];
			cowsy[i][1] = cowsx[i][1];
		}
		Arrays.sort(cowsx, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				if(o1[0] > o2[0]) return 1;
				else if(o1[0] < o2[0]) return -1;
				return 0;
			}
		});
		long[][] yboundr = new long[n][2];
		long[][] yboundl = new long[n][2];
		for(int i = 0; i < n; i++) {
			yboundr[i][0] = Math.max(i > 0 ? yboundr[i - 1][0] : 0, cowsx[i][1]);
			yboundr[i][1] = Math.min(i > 0 ? yboundr[i - 1][1] : Long.MAX_VALUE, cowsx[i][1]);
		}
		for(int i = n - 1; i >= 0; i--) {
			yboundl[i][0] = Math.max(i < n - 1 ? yboundl[i + 1][0] : 0, cowsx[i][1]);
			yboundl[i][1] = Math.min(i < n - 1 ? yboundl[i + 1][1] : Long.MAX_VALUE, cowsx[i][1]);
		}
		long[][] xboundd = new long[n][2];
		long[][] xboundu = new long[n][2];
		Arrays.sort(cowsy, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				if(o1[1] > o2[1]) return 1;
				else if(o1[1] < o2[1]) return -1;
				return 1;
			}
		});
		for(int i = 0; i < n; i++) {
			xboundu[i][0] = Math.max(i > 0 ? xboundu[i - 1][0] : 0, cowsy[i][0]);
			xboundu[i][1] = Math.min(i > 0 ? xboundu[i - 1][1] : Long.MAX_VALUE, cowsy[i][0]);
		}
		for(int i = n - 1; i >= 0; i--) {
			xboundd[i][0] = Math.max(i < n - 1 ? xboundd[i + 1][0] : 0, cowsy[i][0]);
			xboundd[i][1] = Math.min(i < n - 1 ? xboundd[i + 1][1] : Long.MAX_VALUE, cowsy[i][0]);
		}
		long res = Long.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			long horzsplitu = (xboundu[i][0] - xboundu[i][1]) * (cowsy[i][1] - cowsy[0][1]);
			long horzsplitd = i >= n - 1 ? 0 : (xboundd[i + 1][0] - xboundd[i + 1][1]) * (cowsy[n - 1][1] - cowsy[i + 1][1]);
			long vertsplitr = (yboundr[i][0] - yboundr[i][1]) * (cowsx[i][0] - cowsx[0][0]);
			long vertsplitl = i >= n - 1 ? 0 : (yboundl[i + 1][0] - yboundl[i + 1][1]) * (cowsx[n - 1][0] - cowsx[i + 1][0]);
			res = Math.min(res, horzsplitu + horzsplitd);
			res = Math.min(res, vertsplitr + vertsplitl);
		}
		long area = (cowsx[n - 1][0] - cowsx[0][0]) * (cowsy[n - 1][1] - cowsy[0][1]);
		out.println(area - res);
		in.close();
		out.close();
	}
}
