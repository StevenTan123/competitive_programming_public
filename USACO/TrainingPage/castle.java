/*
ID: tanstev1
LANG: JAVA
TASK: castle
 */
import java.util.*;
import java.io.*;

public class castle {
	static HashSet<Integer> north, south, east, west;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		square[][] floorplan = new square[n][m];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++) {
				int val = Integer.parseInt(line.nextToken());
				floorplan[i][j] = new square(val);
			}
		}
		in.close();
		int[][] components = new int[n][m];
		int compnum = 1;
		ArrayList<Integer> compsizes = new ArrayList<Integer>();
		int max = 0;
		compsizes.add(-1);
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				if(components[r][c] == 0) {
					int cursize = generateComponents(floorplan, r, c, components, compnum);
					compsizes.add(cursize);
					if(cursize > max) {
						max = cursize;
					}
					compnum++;
				}
			}
		}
		TreeSet<wall> walls = new TreeSet<wall>();
		boolean[][] visited = new boolean[n][m];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				if(!visited[r][c]) {
					formBigger(components, visited, compsizes, r, c, r, c, walls);
				}
			}
		}
		wall res = walls.pollFirst();
		StringBuilder output = new StringBuilder();
		output.append(res.row + 1);
		output.append(' ');
		output.append(res.col + 1);
		output.append(' ');
		output.append(res.dir);
		PrintWriter out = new PrintWriter("castle.out");
		out.println(compnum - 1);
		out.println(max);
		out.println(res.createdSize);
		out.println(output.toString());
		out.close();
	}
	static void formBigger(int[][] components, boolean[][] visited, ArrayList<Integer> compsizes, int row, int col, int prevr, int prevc, TreeSet<wall> walls) {
		if(row < 0 || col < 0 || row >= components.length || col >= components[0].length || visited[row][col]) {
			return;
		}
		if(components[row][col] != components[prevr][prevc]) {
			int sum = compsizes.get(components[row][col]) + compsizes.get(components[prevr][prevc]);
			if(row < prevr) {
				walls.add(new wall(prevr, prevc, 'N', sum));
			}else if(row > prevr) {
				walls.add(new wall(row, col, 'N', sum));
			}else if(col > prevc) {
				walls.add(new wall(prevr, prevc, 'E', sum));
			}else {
				walls.add(new wall(row, col, 'E', sum));
			}
			return;
		}
		visited[row][col] = true;
		formBigger(components, visited, compsizes, row + 1, col, row, col, walls);
		formBigger(components, visited, compsizes, row, col + 1, row, col, walls);
		formBigger(components, visited, compsizes, row - 1, col, row, col, walls);
		formBigger(components, visited, compsizes, row, col + 1, row, col, walls);
		
	}
	static int generateComponents(square[][] floorplan, int row, int col, int[][] components, int compnum) {
		if(row < 0 || col < 0 || row >= floorplan.length || col >= floorplan[0].length || components[row][col] != 0) {
			return 0;
		}
		components[row][col] = compnum;
		square cursq = floorplan[row][col];
		int sum = 1;
		if(!cursq.north) {
			sum += generateComponents(floorplan, row - 1, col, components, compnum);
		}
		if(!cursq.east) {
			sum += generateComponents(floorplan, row, col + 1, components, compnum);
		}
		if(!cursq.south) {
			sum += generateComponents(floorplan, row + 1, col, components, compnum);
		}
		if(!cursq.west) {
			sum += generateComponents(floorplan, row, col - 1, components, compnum);
		}
		return sum;
	}
	static class square {
		boolean north, east, south, west;
		square(int val) {
			north = false;
			east = false;
			south = false;
			west = false;
			int cur = 8;
			while(val > 0) {
				if(val >= cur) {
					val -= cur;
					if(cur == 8) {
						south = true;
					}else if(cur == 4) {
						east = true;
					}else if(cur == 2) {
						north = true;
					}else {
						west = true;
					}
				}
				cur /= 2;
			}
		}
	}
	static class wall implements Comparable<wall>{
		int row, col, createdSize;
		char dir;
		wall(int row, int col, char dir, int createdSize) {
			this.row = row;
			this.col = col; 
			this.dir = dir;
			this.createdSize = createdSize;
		}
		@Override
		public int compareTo(wall o) {
			if(createdSize > o.createdSize) {
				return -1;
			}else if(createdSize < o.createdSize) {
				return 1;
			}else {
				if(col < o.col) {
					return -1;
				}else if(o.col < col) {
					return 1;
				}else {
					if(row > o.row) {
						return -1;
					}else if(row < o.row) {
						return 1;
					}else {
						if(dir == 'N' && o.dir == 'E') {
							return -1;
						}else if(o.dir == 'N' && dir == 'E') {
							return 1;
						}else {
							return 0;
						}
					}
				}
			}
		}
	}
}
