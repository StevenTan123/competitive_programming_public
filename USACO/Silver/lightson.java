import java.io.*;
import java.util.*;

public class lightson {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] switches = new ArrayList[100*99 + 100];
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(line.nextToken()) - 1;
			int y = Integer.parseInt(line.nextToken()) - 1;
			int a = Integer.parseInt(line.nextToken()) - 1;
			int b = Integer.parseInt(line.nextToken()) - 1;
			if(switches[x * 100 + y] == null) {
				switches[x * 100 + y] = new ArrayList<Integer>();
			}
			switches[x * 100 + y].add(a * 100 + b);
		}
		in.close();
		int[][] rooms = new int[n][n];
		rooms[0][0] = 1;
		PrintWriter out = new PrintWriter("lightson.out");
		int res = 0;
		dfs(rooms, switches, 0, 0, new boolean[n][n]);
		int turnedon = countIllum(rooms);
		int prev = -1;
		while(turnedon > prev) {
			prev = turnedon;
			dfs(rooms, switches, 0, 0, new boolean[n][n]);
			turnedon = countIllum(rooms);
			res = turnedon;
		}
		out.println(res);
		out.close();
	}
	static void dfs(int[][] rooms, ArrayList[] switches, int r, int c, boolean[][] visited) {
		if(r < 0 || r >= rooms.length || c < 0 || c >= rooms.length || visited[r][c] || rooms[r][c] == 0) {
			return;
		}
		visited[r][c] = true;
		ArrayList<Integer> curswitches = switches[c * 100 + r];
		if(curswitches != null) {
			for(int i = 0; i < curswitches.size(); i++) {
				int curhash = curswitches.get(i);
				int a = curhash / 100;
				int b = a == 0 ? curhash : curhash % (a * 100);
				rooms[b][a] = 1;
			}
		}
		dfs(rooms, switches, r + 1, c, visited);
		dfs(rooms, switches, r - 1, c, visited);
		dfs(rooms, switches, r, c + 1, visited);
		dfs(rooms, switches, r, c - 1, visited);
	}
	static int countIllum(int[][] rooms) {
		int count = 0;
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0; j < rooms.length; j++) {
				count += rooms[i][j];
			}
		}
		return count;
	}
}
