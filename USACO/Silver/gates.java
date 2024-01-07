import java.io.*;

public class gates {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("gates.in"));
		int n = Integer.parseInt(in.readLine());
		String path = in.readLine();
		in.close();
		int highest = 0;
		int lowest = 0;
		int rightmost = 0;
		int leftmost = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < n; i++) {
			char c = path.charAt(i);
			if(c == 'N') { y++;
			}else if(c == 'S') { y--;
			}else if(c == 'E') { x++;
			}else { x--; }
			highest = Math.max(highest, y);
			lowest = Math.min(lowest, y);
			rightmost = Math.max(rightmost, x);
			leftmost = Math.min(leftmost, x);
		}
		//0th index of given cell signifies if wall exists north, 1 exists east, 2 south, 3 west
		int[][][] map = new int[highest - lowest + 2][rightmost - leftmost + 2][4];
		x = Math.abs(leftmost) + 1;
		y = highest + 1;
		for(int i = 0; i < n; i++) {
			char c = path.charAt(i);
			if(c == 'N') { 
				y--;
				map[y][x - 1][1] = 1;
				map[y][x][3] = 1;
			}else if(c == 'S') { 
				map[y][x - 1][1] = 1;
				map[y][x][3] = 1;
				y++;
			}else if(c == 'E') { 
			    map[y - 1][x][2] = 1;
				map[y][x][0] = 1;
				x++;
			}else { 
				x--;
				map[y - 1][x][2] = 1;
				map[y][x][0] = 1;
			}
		}
		int compnum = 1;
		int[][] components = new int[map.length][map[0].length];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(components[i][j] == 0) {
					dfs(components, compnum, i, j, map);
					compnum++;
				}
			}
		}
		PrintWriter out = new PrintWriter("gates.out");
		out.println(compnum - 2);
		out.close();
	}
	static void dfs(int[][] components, int compnum, int r, int c, int[][][] map) {
		if(r < 0 || r >= map.length || c < 0 || c >= map[0].length || components[r][c] != 0) {
			return;
		}
		components[r][c] = compnum;
		if(map[r][c][0] == 0) dfs(components, compnum, r - 1, c, map);
		if(map[r][c][1] == 0) dfs(components, compnum, r, c + 1, map);
		if(map[r][c][2] == 0) dfs(components, compnum, r + 1, c, map);
		if(map[r][c][3] == 0) dfs(components, compnum, r, c - 1, map);
	}
}
