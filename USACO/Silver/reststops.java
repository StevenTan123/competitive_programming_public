import java.util.*;
import java.io.*;
public class reststops {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int L = Integer.parseInt(line.nextToken());
		int N = Integer.parseInt(line.nextToken());
		int rf = Integer.parseInt(line.nextToken());
		int rb = Integer.parseInt(line.nextToken());
		int[][] stops = new int[N][2];
		for(int i = 0; i < N; i++) {
			line = new StringTokenizer(in.readLine());
			stops[i][0] = Integer.parseInt(line.nextToken());
			stops[i][1] = Integer.parseInt(line.nextToken());
		}
		in.close();
		int maxindex = 0;
		int afterindex = 1;
		long prevstopdis = 0;
		long tastiness = 0;
		while(maxindex < N) {
			long johntime = 0;
			for(int i = afterindex; i < N; i++) {
				if(stops[i][1] > stops[maxindex][1]) {
					maxindex = i;
				}
			}
			johntime = (stops[maxindex][0] - prevstopdis) * (rf - rb);
			prevstopdis = stops[maxindex][0];
			tastiness += johntime * stops[maxindex][1];
			maxindex += 1;
			afterindex = maxindex + 1;
		}
		PrintWriter out = new PrintWriter("reststops.out");
		out.println(tastiness);
		out.close();
	}
}
/*
Better solution

import java.util.*;
import java.io.*;
public class reststops {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int L = Integer.parseInt(line.nextToken());
		int N = Integer.parseInt(line.nextToken());
		int rf = Integer.parseInt(line.nextToken());
		int rb = Integer.parseInt(line.nextToken());
		int[][] stops = new int[N][3];
		for(int i = 0; i < N; i++) {
			line = new StringTokenizer(in.readLine());
			stops[i][0] = Integer.parseInt(line.nextToken());
			stops[i][1] = Integer.parseInt(line.nextToken());
			stops[i][2] = 0;
		}
		in.close();
		int max = 0;
		for(int i = N - 1; i >= 0; i--) {
			if(stops[i][1] > max) {
				max = stops[i][1];
				stops[i][2] = 1;
			}
		}
		long prevstopdis = 0;
		long totastiness = 0;
		for(int i = 0; i < N; i++) {
			if(stops[i][2] == 1) {
				totastiness += (stops[i][0] - prevstopdis) * (rf - rb) * stops[i][1];
				prevstopdis = stops[i][0];
			}
		}
		PrintWriter out = new PrintWriter("reststops.out");
		out.println(totastiness);
		out.close();
	}
}
*/