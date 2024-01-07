import java.io.*;
import java.util.*;

public class pairup {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] cows = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = Integer.parseInt(line.nextToken());
		}
		in.close();
		Arrays.sort(cows, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		int maxtime = 0;
		int leftind = 0;
		int rightind = n - 1;
		int leftcount = 1;
		int rightcount = cows[rightind][0];
		while(rightind > leftind || (rightind == leftind && rightcount > leftcount)) {
			int cursum = cows[leftind][1] + cows[rightind][1];
			if(cursum > maxtime) {
				maxtime = cursum;
			}
			leftcount++;
			rightcount--;
			if(leftcount >= cows[leftind][0]) {
				leftcount = 1;
				leftind++;
			}
			if(rightcount < 1) {
				rightind--;
				rightcount = cows[rightind][0];
			}
		}
		PrintWriter out = new PrintWriter("pairup.out");
		out.println(maxtime);
		out.close();
	}
}
