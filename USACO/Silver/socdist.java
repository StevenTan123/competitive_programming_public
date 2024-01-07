import java.util.*;
import java.io.*;

public class socdist {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("socdist.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		long[][] intervals = new long[m][2];
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			intervals[i][0] = Integer.parseInt(line.nextToken());
			intervals[i][1] = Integer.parseInt(line.nextToken());
		}
		in.close();
		Arrays.sort(intervals, new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				if(o1[0] > o2[0]) {
					return 1;
				}else if(o1[0] < o2[0]) {
					return -1;
				}else {
					return 0;
				}
			}
		});
		long lbound = 0;
		long rbound = 1000000000;
		while(lbound < rbound - 1) {
			long average = (lbound + rbound) / 2;
			if(canfit(average, intervals, n)) {
				lbound = average;
			}else {
				rbound = average - 1;
			}
		}
		PrintWriter out = new PrintWriter("socdist.out");
		if(rbound > lbound && canfit(rbound, intervals, n)) {
			out.println(rbound);
		}else {
			out.println(lbound);
		}
		out.close();
	}
	static boolean canfit(long d, long[][] intervals, int n) {
		long curpos = 0;
		int cowsused = 0;
		long nextcow = 0;
		for(int i = 0; i < intervals.length; i++) {
			if(nextcow > intervals[i][0]) {
				if(nextcow <= intervals[i][1]) {
					curpos = nextcow;
				}else {
					continue;
				}
			}else {
				curpos = intervals[i][0];
			}
			while(curpos <= intervals[i][1]) {
				curpos += d;
				cowsused++;
			}
			if(i != intervals.length - 1) {
				nextcow = curpos;
			}
		}
		return cowsused >= n;
	}
}
