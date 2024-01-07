import java.io.*;
import java.util.*;

public class closing {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("closing.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		ArrayList[] edges = new ArrayList[n];
		for(int i = 0; i < n; i++) edges[i] = new ArrayList<Integer>();
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			int barn1 = Integer.parseInt(line.nextToken()) - 1;
			int barn2 = Integer.parseInt(line.nextToken()) - 1;
			edges[barn1].add(barn2);
			edges[barn2].add(barn1);
		}
		int[] visited = new int[n];
		PrintWriter out = new PrintWriter("closing.out");
		for(int i = 0; i < n; i++) {
			int barnclosed = -1;
			if(i > 0) {
				barnclosed = Integer.parseInt(in.readLine()) - 1;
				visited[barnclosed] = -1;
			}
			int start = 0;
			for(int j = 0; j < n; j++) {
				if(visited[j] != -1) {
					start = j;
					break;
				}
			}
			int res = countReachable(visited, edges, start);
			if(res == n - i) {
				out.println("YES");
			}else {
				out.println("NO");
			}
			for(int j = 0; j < n; j++) {
				if(visited[j] != -1) {
					visited[j] = 0;
				}
			}
		}
		in.close();
		out.close();
	}
	static int countReachable(int[] visited, ArrayList[] edges, int cur) {
		visited[cur] = 1;
		int sum = 0;
		for(int i = 0; i < edges[cur].size(); i++) {
			int nextbarn = (int)edges[cur].get(i); 
			if(visited[nextbarn] == 0) {
				sum += countReachable(visited, edges, nextbarn);
			}
		}
		return 1 + sum;
	}
}
