import java.util.*;
import java.io.*;

public class _1374_E1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[][] books = new int[n][3];
		ArrayList<Integer> both = new ArrayList<Integer>();
		ArrayList<Integer> alice = new ArrayList<Integer>();
		ArrayList<Integer> bob = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < 3; j++) {
				books[i][j] = Integer.parseInt(line.nextToken());
			}
			if(books[i][1] == 1 && books[i][2] == 1) {
				both.add(books[i][0]);
			}else if(books[i][1] == 1) {
				alice.add(books[i][0]);
			}else if(books[i][2] == 1) {
				bob.add(books[i][0]);
			}
		}
		Collections.sort(both);
		Collections.sort(alice);
		Collections.sort(bob);
		long[] pboth = genprefix(both);
		long[] palice = genprefix(alice);
		long[] pbob = genprefix(bob);
		long res = Long.MAX_VALUE;
		for(int i = 0; i <= pboth.length; i++) {
			long timeused = (i == 0) ? 0 : pboth[i - 1];
			if(k - i - 1 >= palice.length || k - i - 1 >= pbob.length) {
				continue;
			}
		    if(k - i - 1 >= 0) timeused += palice[k - i - 1];
			if(k - i - 1 >= 0) timeused += pbob[k - i - 1];
			res = Math.min(res, timeused);
		}
		out.println(res == Long.MAX_VALUE ? -1 : res);
		in.close();
		out.close();
	}
	static long[] genprefix(ArrayList<Integer> al) {
		long[] prefix = new long[al.size()];
		for(int i = 0; i < prefix.length; i++) {
			prefix[i] = (i > 0) ? prefix[i - 1] + al.get(i) : al.get(i);
		}
		return prefix;
	}
}
