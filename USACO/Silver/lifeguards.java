import java.util.*;
import java.io.*;
public class lifeguards {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] lifeguards = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			lifeguards[i][0] = Integer.parseInt(line.nextToken());
			lifeguards[i][1] = Integer.parseInt(line.nextToken());
		}
		in.close();
		Arrays.sort(lifeguards, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[0], b[0]);
			}
		});
		int curind = 0;
		int curalone = lifeguards[0][1] - lifeguards[0][0];
		int min = curalone;
		int timecovered = curalone;
		for(int i = 1; i < lifeguards.length; i++) {
			if(lifeguards[i][0] < lifeguards[curind][1]) {
				if(lifeguards[i][1] <= lifeguards[curind][1]) {
					curalone -= lifeguards[i][1] - lifeguards[i][0];
					min = 0;
				}else {
					int overlap = lifeguards[curind][1] - lifeguards[i][0]; 
					curalone -= overlap;
					if(curalone < min) {
						min = curalone;
					}
					curind = i;
					curalone = lifeguards[curind][1] - lifeguards[curind][0] - overlap;
					if(curalone < min) {
						min = curalone;
					}
					timecovered += curalone;
				}
			}else {
				if(curalone < min) {
					min = curalone;
				}
				curind = i;
				curalone = lifeguards[curind][1] - lifeguards[curind][0];
				if(curalone < min) {
					min = curalone;
				}
				timecovered += curalone;
			}
		}
		if(min < 0) {
			min = 0;
		}
		PrintWriter out = new PrintWriter("lifeguards.out");
		out.println(timecovered - min);
		out.close();
	}
}
