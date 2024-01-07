import java.io.*;
import java.util.*;

public class haybales {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int q = Integer.parseInt(line.nextToken());
		int[] haybales = new int[n];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			haybales[i] = Integer.parseInt(line.nextToken());
		}
		int[][] queries = new int[q][2];
		TreeMap<Integer, Integer> balesRange = new TreeMap<Integer, Integer>();
		for(int i = 0; i < q; i++) {
			line = new StringTokenizer(in.readLine());
			queries[i][0] = Integer.parseInt(line.nextToken());
			queries[i][1] = Integer.parseInt(line.nextToken());
			balesRange.put(queries[i][0] - 1, null);
			balesRange.put(queries[i][1], null);
		}
		in.close();
		Arrays.sort(haybales);
		int balecount = 0;
		int prevqueryind = 0;
		for(Integer querylim : balesRange.keySet()) {
			while(prevqueryind < n && haybales[prevqueryind] <= querylim) {
				prevqueryind++;
				balecount++;
			}
			balesRange.put(querylim, balecount);
		}
		PrintWriter out = new PrintWriter("haybales.out");
		for(int i = 0; i < q; i++) {
			out.println(balesRange.get(queries[i][1]) - balesRange.get(queries[i][0] - 1));
		}
		out.close();
	}
}
