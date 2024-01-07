import java.util.*;
import java.io.*;

public class milkvisits {
	static int componentnum;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		String strfarms = in.readLine();
		int[] farms = new int[n];
		friend[] friends = new friend[m];
		for(int i = 0; i < n; i++) {
			char c = strfarms.charAt(i);
			if(c == 'G') {
				farms[i] = 1;
			}else {
				farms[i] = 0;
			}
		}
		HashMap<Integer, ArrayList<Integer>> tree = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < n - 1; i++) {
			line = new StringTokenizer(in.readLine());
			int farm1 = Integer.parseInt(line.nextToken()) - 1;
			int farm2 = Integer.parseInt(line.nextToken()) - 1;
			if(tree.containsKey(farm1)) {
				tree.get(farm1).add(farm2);
			}else {
				ArrayList<Integer> val = new ArrayList<Integer>();
				val.add(farm2);
				tree.put(farm1, val);
			}
			if(tree.containsKey(farm2)) {
				tree.get(farm2).add(farm1);
			}else {
				ArrayList<Integer> val = new ArrayList<Integer>();
				val.add(farm1);
				tree.put(farm2, val);
			}
		}
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int begin = Integer.parseInt(line.nextToken()) - 1;
			int end = Integer.parseInt(line.nextToken()) - 1;
			int preferred = line.nextToken().equals("G") ? 1 : 0;
			friends[i] = new friend(begin, end, preferred);
		}
		in.close();
		int[] components = new int[n];
		search(tree, farms, new int[n], components, 0, farms[0], 0);
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < m; i++) {
			if(components[friends[i].begin] == components[friends[i].end]) {
				if(farms[friends[i].begin] == friends[i].preferred) {
					res.append(1);
				}else {
					res.append(0);
				}
			}else {
				res.append(1);
			}
		}
		PrintWriter out = new PrintWriter("milkvisits.out");
		out.println(res);
		out.close();
	}	
	static void search(HashMap<Integer, ArrayList<Integer>> tree, int[] farms, int[] visited, int[] components, int cur, int prevtype, int prevcompnum) {
		if(visited[cur] == 1) {
			return;
		}
		visited[cur] = 1;
		if(farms[cur] != prevtype) {
			componentnum++;
			components[cur] = componentnum;
			prevtype = farms[cur];
		}else {
			components[cur] = prevcompnum;
		}
		ArrayList<Integer> neighborFarms = tree.get(cur);
		for(int i = 0; i < neighborFarms.size(); i++) {
			search(tree, farms, visited, components, neighborFarms.get(i), prevtype, components[cur]);
		}
	}
	static class friend {
		int begin, end, preferred;
		friend(int begin, int end, int preferred){
			this.begin = begin;
			this.end = end;
			this.preferred = preferred;
		}
	}
}
