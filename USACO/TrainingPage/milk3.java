/*
ID: tanstev1
LANG: JAVA
PROB: milk3
 */
import java.util.*;
import java.io.*;

public class milk3 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int[] caps = new int[] {Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
		int[] buckets = new int[] {0, 0, caps[2]};
		boolean[][][] visited = new boolean[21][21][21];
		in.close();
		TreeSet<Integer> res = new TreeSet<Integer>();
		dfs(res, buckets, caps, visited);
		PrintWriter out = new PrintWriter("milk3.out");
		int counter = 0;
		for(int i : res) {
			if(counter == 0) {
				out.print(i);
			}else {
				out.print(" " + i);
			}
			counter++;
		}
		out.println();
		out.close();
	}
	static void dfs(TreeSet<Integer> res, int[] buckets, int[] caps, boolean[][][] visited) {
		if(visited[buckets[0]][buckets[1]][buckets[2]]) {
			return;
		}
		if(buckets[0] == 0) {
			res.add(buckets[2]);
		}
		visited[buckets[0]][buckets[1]][buckets[2]] = true;
		pour(res, buckets, caps, 0, 1, visited);
		pour(res, buckets, caps, 0, 2, visited);
		pour(res, buckets, caps, 1, 0, visited);
		pour(res, buckets, caps, 1, 2, visited);
		pour(res, buckets, caps, 2, 0, visited);
		pour(res, buckets, caps, 2, 1, visited);
	}
	static void pour(TreeSet<Integer> res, int[] buckets, int[] caps, int from, int to, boolean[][][] visited) {
		int olda = buckets[0];
		int oldb = buckets[1];
		int oldc = buckets[2];
		if(buckets[from] == 0 || buckets[to] == caps[to]) {
			return;
		}
		int spaceinto = caps[to] - buckets[to];
		if(buckets[from] >= spaceinto) {
			buckets[from] -= spaceinto;
			buckets[to] = caps[to];
		}else {
			buckets[to] += buckets[from];
			buckets[from] = 0;
		}
		dfs(res, buckets, caps, visited);
		buckets[0] = olda;
		buckets[1] = oldb;
		buckets[2] = oldc;
	}
}
