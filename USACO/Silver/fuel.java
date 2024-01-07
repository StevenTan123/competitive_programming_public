import java.util.*;
import java.io.*;

public class fuel {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fuel.in"));
		PrintWriter out = new PrintWriter("fuel.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int g = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		int d = Integer.parseInt(line.nextToken());
		int[][] stations = new int[n + 1][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			stations[i][0] = Integer.parseInt(line.nextToken());
			stations[i][1] = Integer.parseInt(line.nextToken());
		}
		stations[n][0] = d;
		stations[n][1] = 0;
		Arrays.sort(stations, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o2[1] - o1[1];
				return o1[0] - o2[0];
			}
		});
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		int[] nextcheaper = new int[n + 1];
		for(int i = n; i >= 0; i--) {
			while(stack.size() > 0 && stations[stack.peek()][1] >= stations[i][1]) {
				stack.pop();
			}
			if(stack.size() > 0) nextcheaper[i] = stack.peek();
			stack.push(i);
		}
		long totalcost = 0;
		long curgas = b;
		for(int i = 0; i <= n; i++) {
			if(i > 0) curgas -= stations[i][0] - stations[i - 1][0];
			else curgas -= stations[i][0];
			if(curgas < 0) {
				totalcost = -1;
				break;
			}
			if(nextcheaper[i] > 0 && stations[nextcheaper[i]][0] - stations[i][0] <= g) {
				if(stations[nextcheaper[i]][0] - stations[i][0] > curgas) {
					totalcost += (stations[nextcheaper[i]][0] - stations[i][0] - curgas) * stations[i][1];
					curgas = stations[nextcheaper[i]][0] - stations[i][0];
				}
			}else {
				totalcost += (g - curgas) * stations[i][1];
				curgas = g;
			}
		}
		out.println(totalcost);
		in.close();
		out.close();
	}
}
