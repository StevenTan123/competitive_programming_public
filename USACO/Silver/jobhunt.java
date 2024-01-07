import java.util.*;
import java.io.*;

public class jobhunt {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int d = Integer.parseInt(line.nextToken());
		int p = Integer.parseInt(line.nextToken());
		int c = Integer.parseInt(line.nextToken());
		int f = Integer.parseInt(line.nextToken());
		int s = Integer.parseInt(line.nextToken()) - 1;
		edge[] edges = new edge[p + f];
		for(int i = 0; i < p; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			edges[i] = new edge(v1, v2, -d);
		}
		for(int i = 0; i < f; i++) {
			line = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(line.nextToken()) - 1;
			int v2 = Integer.parseInt(line.nextToken()) - 1;
			int weight = Integer.parseInt(line.nextToken());
			edges[i + p] = new edge(v1, v2, weight - d);
		}
		int[] distances = new int[c];
		int bigboi = 1000000007;
		Arrays.fill(distances, bigboi);
		distances[s] = -d;
		for(int i = 0; i < c; i++) {
			for(int j = 0; j < edges.length; j++) {
				edge curedge = edges[j];
				if(distances[curedge.v2] > distances[curedge.v1] + curedge.weight) {
					distances[curedge.v2] = distances[curedge.v1] + curedge.weight;
					if(i == c - 1) {
						out.println(-1);
						out.close();
						return;
					}
				}
			}
		}
		int min = bigboi;
		for(int i = 0; i < c; i++) {
			min = Math.min(min, distances[i]);
		}
		out.println(min * -1);
		in.close();
		out.close();
	}
	static class edge {
		int v1, v2, weight;
		edge(int v11, int v22, int w) {
			v1 = v11;
			v2 = v22;
			weight = w;
		}
	}
}
