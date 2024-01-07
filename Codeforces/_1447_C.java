import java.util.*;
import java.io.*;

public class _1447_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			Long W = Long.parseLong(line.nextToken());
			Long half = W / 2;
			if(W % 2 != 0) {
				half++;
			}
			int[][] items = new int[n][2];
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				items[j][0] = Integer.parseInt(line.nextToken());
				items[j][1] = j;
			}
			Arrays.sort(items, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			ArrayList<Integer> indices = new ArrayList<Integer>();
			boolean lessFound = false;
			Long lessSum = (long)0;
			boolean solFound = false;
			for(int j = n - 1; j >= 0; j--) {
				if(items[j][0] <= W && items[j][0] >= half) {
					indices.add(items[j][1]);
					lessSum = (long)items[j][0];
					solFound = true;
					break;
				}
				if(!lessFound && items[j][0] < half) {
					lessFound = true;
				}
				if(lessFound) {
					indices.add(items[j][1]);
					lessSum += items[j][0];
					if(lessSum >= half && lessSum <= W) {
						solFound = true;
						break;
					}
				}
			}
			if(solFound) {
				Collections.sort(indices);
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < indices.size(); j++) {
					sb.append(indices.get(j) + 1);
					sb.append(' ');
				}
				out.println(indices.size());
				out.println(sb.toString());
			}else {
				out.println(-1);
			}
		}
		in.close();
		out.close();
	}
}
