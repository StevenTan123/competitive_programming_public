import java.util.*;
import java.io.*;

public class _1480_D1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		StringTokenizer line = new StringTokenizer(in.readLine());
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
		}
		int[] prevseen = new int[n + 1];
		Arrays.fill(prevseen, -1);
		int[] next = new int[n];
		for(int i = n - 1; i >= 0; i--) {
			if(prevseen[a[i]] == -1) {
				next[i] = Integer.MAX_VALUE;
			}else {
				next[i] = prevseen[a[i]];
			}
			prevseen[a[i]] = i;
		}
		int white = -1;
		int black = -1;
		int res = 0;
		for(int i = 0; i < n; i++) {
			if(i == 0) {
				white = i;
				res++;
			}else if(a[i] == a[white]) {
				if(black == -1 || a[i] != a[black]) res++;
				black = i;
			}else if(black != -1 && a[i] == a[black]) {
				if(a[i] != a[white]) res++;
				white = i;
			}else {
				if(black == -1 || next[white] < next[black]) {
					white = i;
				}else {
					black = i;
				}
				res++;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}
