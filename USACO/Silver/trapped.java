import java.util.*;
import java.io.*;

public class trapped {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("trapped.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		int[][] haybales = new int[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			int size = Integer.parseInt(line.nextToken());
			int position = Integer.parseInt(line.nextToken());
			haybales[i][0] = position;
			haybales[i][1] = size;
		}
		in.close();	
		Arrays.sort(haybales, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		int rightb = 0;
		int leftb = 0;
		for(int i = 0; i < n; i++) {
			if(haybales[i][0] > b) {
				rightb = i;
				leftb = i - 1;
				break;
			}
		}
		if(rightb == 0) {
			rightb = n;
			leftb = n - 1;
		}
		int rightcounter = leftb + 1;
		int mustaddleft = Integer.MAX_VALUE;
		for(int i = leftb; i >= 0; i--) {
			while(rightcounter < n) {
				int d = haybales[rightcounter][0] - haybales[i][0];
				if(d > haybales[rightcounter][1]) {
					rightcounter++;
				}else {
					int curadd = d - haybales[i][1];
					if(curadd < 0) {
						curadd = 0;
					}
					mustaddleft = Math.min(mustaddleft, curadd);
					break;
				}
			}
		}
		int leftcounter = rightb - 1;
		int mustaddright = Integer.MAX_VALUE;
		for(int i = rightb; i < n; i++) {
			while(leftcounter >= 0) {
				int d = haybales[i][0] - haybales[leftcounter][0];
				if(d > haybales[leftcounter][1]) {
					leftcounter--;
				}else {
					int curadd = d - haybales[i][1];
					if(curadd < 0) {
						curadd = 0;
					}
					mustaddright = Math.min(mustaddright, curadd);
					break;
				}
			}
		}
		int res = Math.min(mustaddleft, mustaddright);
		if(res == Integer.MAX_VALUE) {
			res = -1;
		}
		PrintWriter out = new PrintWriter("trapped.out");
		out.println(res);
		out.close();
	}
}
