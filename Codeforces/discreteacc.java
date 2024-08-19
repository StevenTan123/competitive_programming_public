import java.util.*;
import java.io.*;
 
public class discreteacc {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int l = Integer.parseInt(line.nextToken());
			int[] a = new int[n + 2];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				a[j + 1] = Integer.parseInt(line.nextToken());
			}
			a[0] = 0;
			a[n + 1] = l;
			double[][] intervaltimes = new double[n + 2][2];
			int[][] speeds = new int[n + 2][2];
			int lspeed = 1;
			int rspeed = 1;
			for(int j = 0; j < n + 2; j++) {
				int prev = (j > 0) ? a[j - 1] : 0;
				intervaltimes[j][0] = (double)(a[j] - prev) / (double)(lspeed);
				if(j > 0) {
				    intervaltimes[j][0] += intervaltimes[j - 1][0];
				    lspeed++;
				}
				speeds[j][0] = lspeed;
				
				int j2 = n + 2 - j - 1;
				prev = (j2 < n + 1) ? a[j2 + 1] : l;
				intervaltimes[j2][1] = (double)(prev - a[j2]) / (double)(rspeed);
				if(j2 < n + 1) {
				    intervaltimes[j2][1] += intervaltimes[j2 + 1][1];
				    rspeed++;
				}
				speeds[j2][1] = rspeed;
			}
			double res = 0;
			for(int j = 0; j < n + 2; j++) {
				if(intervaltimes[j][0] >= intervaltimes[j][1]) {
					double lstart = a[j - 1];
					double rstart = a[j];
					double ltime = intervaltimes[j - 1][0];
					double rtime = intervaltimes[j][1];
					if(ltime > rtime) {
						rstart = a[j] - (speeds[j][1] * (double)(ltime - rtime));
					}else {
						lstart = a[j - 1] + (speeds[j - 1][0] * ((double)(rtime - ltime)));
					}
					double time = (rstart - lstart) / (speeds[j - 1][0] + speeds[j][1]);
					res = Math.max(ltime, rtime) + time;
					break;
				}
			}
			out.println((float)res);
		}
		in.close();
		out.close();
	}
}