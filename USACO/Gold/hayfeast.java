import java.util.*;
import java.io.*;

public class hayfeast {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter out = new PrintWriter("hayfeast.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		long m = Long.parseLong(line.nextToken());
		int[][] haybales = new int[n][3];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			haybales[i][0] = Integer.parseInt(line.nextToken());
			haybales[i][1] = Integer.parseInt(line.nextToken());
			haybales[i][2] = i;
		}
		TreeSet<int[]> spicy = new TreeSet<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[2] - o2[2];
				}
				return o1[1] - o2[1];
			}
		});
		long tasty = 0;
		int start = 0;
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			tasty += haybales[i][0];
			spicy.add(haybales[i]);
			if(tasty >= m) {
				res = Math.min(res, spicy.last()[1]);
				spicy.remove(haybales[start]);
				tasty -= haybales[start][0];
				start++;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}
