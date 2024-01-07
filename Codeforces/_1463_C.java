import java.util.*;
import java.io.*;

public class _1463_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[][] commands = new int[n][2];
			for(int j = 0; j < n; j++) {
				StringTokenizer line = new StringTokenizer(in.readLine());
				commands[j][0] = Integer.parseInt(line.nextToken());
				commands[j][1] = Integer.parseInt(line.nextToken());
			}
			long prevstarttime = commands[0][0];
			long prevx = 0;
			long dest = commands[0][1];
			long endtime = Math.abs(dest - prevx) + prevstarttime;
			long[] poses = new long[n + 1];
			poses[0] = prevx;
			for(int j = 1; j < n; j++) {
				if(commands[j][0] >= endtime) {
					prevstarttime = commands[j][0];
					prevx = dest;
					dest = commands[j][1];
					endtime = Math.abs(dest - prevx) + prevstarttime;
				}
				int dir = dest > prevx ? 1 : -1;
				long curpos = (commands[j][0] - prevstarttime) * dir + prevx;
				poses[j] = curpos;
			}
			poses[n] = dest;
			int count = 0;
			for(int j = 0; j < n; j++) {
				long min = Math.min(poses[j], poses[j + 1]);
				long max = Math.max(poses[j], poses[j + 1]);
				if(commands[j][1] >= min && commands[j][1] <= max) {
					count++;
				}
			}
			out.println(count);
		}
		in.close();
		out.close();
	}
}
