import java.util.*;
import java.io.*;

public class bgm {
	static String[] letters = new String[] {"B", "E", "S", "I", "G", "O", "M"};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("bgm.in"));
		int n = Integer.parseInt(in.readLine());
		HashMap<String, int[]> possible = new HashMap<String, int[]>();
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			String s = line.nextToken();
			int val = Integer.parseInt(line.nextToken()) % 7;
			if(val < 0) {
				val = 7 + val;
			}
			if(possible.containsKey(s)) {
				possible.get(s)[val]++;
			}else {
				possible.put(s, new int[7]);
				possible.get(s)[val]++;
			}
		}
		in.close();
		PrintWriter out = new PrintWriter("bgm.out");
		out.println(dfs(0, possible, new int[7][2]));
		out.close();
	}
	static long dfs(int cur, HashMap<String, int[]> possible, int[][] chosen) {
		if(cur >= 7) {
			return seqWorks(chosen);
		}
		long sum = 0;
		for(int val = 0; val < possible.get(letters[cur]).length; val++) {
			int repeat = possible.get(letters[cur])[val];
			if(repeat > 0) {
				chosen[cur][0] = val;
				chosen[cur][1] = repeat;
				sum += dfs(cur + 1, possible, chosen);
				chosen[cur] = new int[2];
			}
		}
		return sum;
	}
	static long seqWorks(int[][] chosen) {
		long bessie = chosen[0][0] + 2 * chosen[1][0] + 2 * chosen[2][0] + chosen[3][0];
		long goes = chosen[4][0] + chosen[5][0] + chosen[1][0] + chosen[2][0];
		long moo = chosen[6][0] + 2 * chosen[5][0];
		long combs = 1;
		for(int i = 0; i < 7; i++) {
			combs *= chosen[i][1];
		}
		return (bessie * goes * moo) % 7 == 0 ? combs : 0;
	}
	static class possibility implements Comparable<possibility>{
		int num, repeat;
		possibility(int n){
			num = n;
			repeat = 0;
		}
		@Override
		public int compareTo(possibility o) {
			return num - o.num;
		}
	}
}
