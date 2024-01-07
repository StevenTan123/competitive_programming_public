import java.io.*;
import java.util.*;

public class pails {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pails.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(line.nextToken());
		int y = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		in.close();
		TreeSet<Integer> difs = new TreeSet<Integer>();
		dfs(x, y, k, m, 0, 0, 0, new boolean[101][101], difs);
		PrintWriter out = new PrintWriter("pails.out");
		out.println(difs.first());
		out.close();
	}
	static void dfs(int x, int y, int k, int m, int level, int inx, int iny, boolean[][] visited, TreeSet<Integer> difs) {
		if(visited[inx][iny] || level >= k) {
			return;
		}
		visited[inx][iny] = true;
		difs.add(Math.abs(m - (inx + iny)));
		dfs(x, y, k, m, level + 1, x, iny, visited, difs);
		dfs(x, y, k, m, level + 1, inx, y, visited, difs);
		dfs(x, y, k, m, level + 1, 0, iny, visited, difs);
		dfs(x, y, k, m, level + 1, inx, 0, visited, difs);
		int pourx = inx + iny;
		int poury;
		if(pourx >= x) {
			pourx = x;
			poury = iny - (x - inx);
		}else {
			poury = 0;
		}
		dfs(x, y, k, m, level + 1, pourx, poury, visited, difs);
		poury = iny + inx;
		if(poury >= y) {
			poury = y;
			pourx = inx - (y - iny);
		}else {
			pourx = 0;
		}
		dfs(x, y, k, m, level + 1, pourx, poury, visited, difs);
	}
}
