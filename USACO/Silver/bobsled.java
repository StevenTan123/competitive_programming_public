import java.util.*;
import java.io.*;

public class bobsled {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int l = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		int[][] turns = new int[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			int ti = Integer.parseInt(line.nextToken());
			int si = Integer.parseInt(line.nextToken());
			turns[i][0] = ti;
			turns[i][1] = si;
		}
		Arrays.sort(turns, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		for(int i = n - 1; i >= 1; i--) {
			int xdif = turns[i][0] - turns[i - 1][0];
			turns[i - 1][1] = Math.min(turns[i - 1][1], turns[i][1] + xdif);
		}
		int maxheight = 0;
		int prevheight = 1;
		int prevtime = 0;
		for(int i = 0; i <= n; i++) {
			if(i == n) {
				int xdif = l - prevtime;
				maxheight = Math.max(xdif + prevheight, maxheight);
				continue;
			}
			int a = turns[i][0] - prevtime;
			int b = turns[i][1] - prevheight;
			if(b <= a) {
				int curheight = (a + b) / 2 + prevheight;
				maxheight = Math.max(maxheight, curheight);
				prevheight = turns[i][1];
			}else {
				maxheight = Math.max(a + prevheight, maxheight);
				prevheight = a + prevheight;
			}
			prevtime = turns[i][0];
		}
		out.println(maxheight);
		in.close();
		out.close();
	}
}
