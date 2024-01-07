import java.util.*;
import java.io.*;

public class meetings {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("meetings.in"));
		PrintWriter out = new PrintWriter("meetings.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int l = Integer.parseInt(line.nextToken());
		int[][] cows = new int[n][3];
		long weightsum = 0;
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = Integer.parseInt(line.nextToken());
			cows[i][2] = Integer.parseInt(line.nextToken());
			weightsum += cows[i][0];
		}
		int[][] cows2 = new int[n][3];
		for(int i = 0; i < n; i++) {
			cows2[i][0] = cows[n - i - 1][0];
			cows2[i][1] = l - cows[n - i - 1][1];
			cows2[i][2] = cows[n - i - 1][2] * -1;
		}
		Comparator<int[]> comp = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		};
		Arrays.sort(cows, comp);
		Arrays.sort(cows2, comp);
		ArrayList<pair> reaches = new ArrayList<pair>();
		add_reaches(cows, reaches);
		add_reaches(cows2, reaches);
		Collections.sort(reaches, new Comparator<pair>() {
			@Override
			public int compare(pair o1, pair o2) {
				return o1.t - o2.t;
			}
		});
		long cursum = 0;
		int t = 0;
		for(pair p : reaches) {
			cursum += p.w;
			if(cursum * 2 >= weightsum) {
				t = p.t;
				break;
			}
		}
		ArrayList<Integer> right = new ArrayList<Integer>();
		ArrayList<Integer> left = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			if(cows[i][2] == 1) right.add(cows[i][1]);
			else left.add(cows[i][1]);	
		}
		int lpointer = 0;
		int rpointer = 0;
		int collisions = 0;
		for(int i = 0; i < right.size(); i++) {
			while(lpointer < left.size() && left.get(lpointer) < right.get(i)) {
				lpointer++;
			}
			while(rpointer < left.size() && left.get(rpointer) - right.get(i) <= t * 2) {
				rpointer++;
			}
			collisions += rpointer - lpointer;
		}
		out.println(collisions);
		in.close();
		out.close();
	}
	static void add_reaches(int[][] cows, ArrayList<pair> reaches) {
		ArrayDeque<Integer> rights = new ArrayDeque<Integer>();
		for(int i = 0; i < cows.length; i++) {
			if(cows[i][2] == 1)  {
				rights.add(cows[i][0]);
			}else {
				rights.add(cows[i][0]);
				int weight = rights.poll();
				reaches.add(new pair(cows[i][1], weight));
			}
		}
	}
	static class pair {
		int t, w;
		pair(int tt, int ww) {
			t = tt;
			w = ww;
		}
	}
}
