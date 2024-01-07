import java.io.*;
import java.util.*;
public class fenceplan {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("fenceplan.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		HashMap<Integer, ArrayList<Integer>> connections = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < n; i++) {
			connections.put(i + 1, new ArrayList<Integer>());
		}
		int[][] cows = new int[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = Integer.parseInt(line.nextToken());
		}
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int cow1 = Integer.parseInt(line.nextToken());
			int cow2 = Integer.parseInt(line.nextToken());
			connections.get(cow1).add(cow2);
			connections.get(cow2).add(cow1);
		}
		in.close();
		ArrayList<ArrayList<Integer>> moonetworks = new ArrayList<ArrayList<Integer>>();
		boolean[] visited = new boolean[n];
		int connum = -1;
		for(int i = 0; i < n; i++) {
			if(visited[i] == false) {
				connum++;
				moonetworks.add(new ArrayList<Integer>());
				find1Network(moonetworks, visited, i, connections, connum);
			}
		}
		int minperimeter = -1;
		for(int i = 0; i < moonetworks.size(); i++) {
			ArrayList<Integer> cur = moonetworks.get(i);
			int[] cow0 = cows[cur.get(0)];
			int minx = cow0[0];
		    int miny = cow0[1]; 
		    int maxx = cow0[0];
		    int maxy = cow0[1];
			for(int j = 1; j < cur.size(); j++) {
				int[] curcow = cows[cur.get(j)];
				if(curcow[0] < minx) {
					minx = curcow[0];
				}
				if(curcow[0] > maxx) {
					maxx = curcow[0];
				}
				if(curcow[1] > maxy) {
					maxy = curcow[1];
				}
				if(curcow[1] < miny) {
					miny = curcow[1];
				}
			}
			int curperimeter = (maxx - minx) * 2 + (maxy - miny) * 2;
			if(minperimeter == -1) {
				minperimeter = curperimeter;
			}else if(curperimeter < minperimeter) {
				minperimeter = curperimeter;
			}
		}
		PrintWriter out = new PrintWriter("fenceplan.out");
		out.println(minperimeter);
		out.close();
	}
	static void find1Network(ArrayList<ArrayList<Integer>> moonetworks, boolean[] visited, int curcow, HashMap<Integer, ArrayList<Integer>> connections, int connum) {
		if(visited[curcow] == true) {
			return;
		}
		moonetworks.get(connum).add(curcow);
		visited[curcow] = true;
		ArrayList<Integer> curCon = connections.get(curcow + 1);
		for(int i = 0; i < curCon.size(); i++) {
			find1Network(moonetworks, visited, curCon.get(i) - 1, connections, connum);
		}
	}
	
}
