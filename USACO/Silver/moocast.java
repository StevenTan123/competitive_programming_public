import java.util.*;
import java.io.*;

public class moocast {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		int n = Integer.parseInt(in.readLine());
		cow[] cows = new cow[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			cows[i] = new cow(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
		}
		in.close();
		HashMap<Integer, ArrayList<Integer>> inpower = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < cows.length; i++) {
			inpower.put(i, new ArrayList<Integer>());
			for(int j = 0; j < cows.length; j++) {
				if(j != i) {
					double distance = distance(cows[j].x, cows[j].y, cows[i].x, cows[i].y);
					if(distance <= cows[i].p) {
						inpower.get(i).add(j);
					}
				}
			}
		}
		HashSet<Integer> visited;
		int maxcows = 0;
		for(int i = 0; i < cows.length; i++) {
			visited = new HashSet<Integer>();
			int res = broadcast(i, -1, inpower, cows, visited);
			if(res > maxcows) {
				maxcows = res;
			}
		}
		PrintWriter out = new PrintWriter("moocast.out");
		out.println(maxcows);
		out.close();
	}
	static class cow {
		int x, y, p;
		cow(int x, int y, int p) {
			this.x = x;
			this.y = y;
			this.p = p;
		}
	}
	static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + (Math.pow(y2 - y1, 2)));
	}
	static int broadcast(int curcow, int prevcow, HashMap<Integer, ArrayList<Integer>> inpower, cow[] cows, HashSet<Integer> visited) {
		int cowsinrange = 0;
		if(visited.contains(curcow)) {
			return 0;
		}else {
			if(prevcow < 0 || distance(cows[curcow].x, cows[curcow].y, cows[prevcow].x, cows[curcow].y) < cows[prevcow].p) {
				cowsinrange++;
			}
		}
		visited.add(curcow);
		ArrayList<Integer> incurpower = inpower.get(curcow);
		for(int i = 0; i < incurpower.size(); i++) {
			cowsinrange += broadcast(incurpower.get(i), curcow, inpower, cows, visited);
		}
		return cowsinrange;
	}
}
