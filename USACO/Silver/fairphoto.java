import java.util.*;
import java.io.*;

public class fairphoto {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fairphoto.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] cows = new int[n][2];
		int[] prefixsums = new int[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = line.nextToken().equals("S") ? -1 : 1;
		}
		in.close();
		Arrays.sort(cows, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		ArrayList<cow> evens = new ArrayList<cow>();
		ArrayList<cow> odds = new ArrayList<cow>();
		int cursum = 0;
		for(int i = 0; i < n; i++) {
			
			cursum += cows[i][1];
			prefixsums[i] = cursum;
			if(i % 2 == 0) {
				evens.get(i / 2).prefixsum = cursum;
			}else {
				odds.get(i / 2).prefixsum = cursum;
			}
		}
		int maxsize = 0;
		for(int i = n - 1; i >= 0; i--) {
			if(i % 2 == 0) {
				evens.remove(new cow(prefixsums[i], i));
				int target = prefixsums[i] - cows[i][1];
				int res = binarySearch(odds, target);
				if(res > -1) {
					maxsize = Math.max(maxsize, cows[i][0] - cows[odds.get(res).id][0]);
				}
			}else {
				odds.remove(new cow(prefixsums[i], i));
				int target = prefixsums[i] - cows[i][1];
				int res = binarySearch(evens, target);
				if(res > -1) {
					maxsize = Math.max(maxsize, cows[i][0] - cows[evens.get(res).id][0]);
				}
			}
		}
		PrintWriter out = new PrintWriter("fairphoto.out");
		out.println(maxsize);
		out.close();
	}
	static int binarySearch(ArrayList<cow> parity, int target) {
		int lbound = 0;
		int rbound = parity.size() - 1;
		while(lbound < rbound) {
			int average = (lbound + rbound) / 2;
			if(parity.get(average).prefixsum > target) {
				lbound = average + 1;
			}else if(parity.get(average).prefixsum < target) {
				rbound = average - 1;
			}else {
				rbound = average;
			}
		}
		if(parity.get(lbound).prefixsum < target) {
			if(lbound >= parity.size() - 1) {
				return -1;
			}else {
				return lbound + 1;
			}
		}else {
			return lbound;
		}
	}
	static class cow implements Comparable<cow> {
		int prefixsum, id;
		cow(int i) {
			id = i;
		}
		cow(int p, int i) {
			prefixsum = p;
			id = i;
		}
		public int compareTo(cow o) {
			if(prefixsum > o.prefixsum) {
				return 1;
			}else if(o.prefixsum > prefixsum) {
				return -1;
			}else {
				return id - o.id;
			}
		}
	}
}
