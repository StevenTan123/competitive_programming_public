import java.util.*;
import java.io.*;

public class _1136_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[][] a = new int[n][m];
		int[][] b = new int[n][m];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++) {
				a[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++) {
				b[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		boolean possible = true;
		for(int i = 0; i <= m + n - 2; i++) {
			ArrayList<Integer> d1 = diagonal(a, n, m, i);
			ArrayList<Integer> d2 = diagonal(b, n, m, i);
			if(!equal(d1, d2)) {
				possible = false;
				break;
			}
		}
		out.println(possible ? "YES" : "NO");
		in.close();
		out.close();
	}
	static ArrayList<Integer> diagonal(int[][] arr, int n, int m, int sum) {
		ArrayList<Integer> diag = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			if(sum - i >= m) continue;
			if(sum - i < 0) continue;
			diag.add(arr[i][sum - i]);
		}
		Collections.sort(diag);
		return diag;
	}
	static boolean equal(ArrayList<Integer> a1, ArrayList<Integer> a2) {
		if(a1.size() != a2.size()) return false;
		for(int i = 0; i < a1.size(); i++) {
			if(!a1.get(i).equals(a2.get(i))) return false;
		}
		return true;
	}
}
