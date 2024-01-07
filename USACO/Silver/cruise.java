import java.util.*;
import java.io.*;

public class cruise {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cruise.in"));
		PrintWriter out = new PrintWriter("cruise.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[][] graph = new int[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			graph[i][0] = Integer.parseInt(line.nextToken()) - 1;
			graph[i][1] = Integer.parseInt(line.nextToken()) - 1;
		}
		line = new StringTokenizer(in.readLine());
		String str = "";
		for(int i = 0; i < m; i++) {
			str += line.nextToken();
		}
		int[][] visited = new int[n][m];
		for(int i = 0; i < n; i++) Arrays.fill(visited[i], -1);
		int pos = 0;
		int port = 0;
		int cyc_start = -1;
		int cyc_length = -1;
		int port_start = -1;
		while(true) {
			if(visited[port][pos % m] != -1) {
				cyc_start = visited[port][pos % m];
				port_start = port;
				cyc_length = pos - cyc_start;
				break;
			}
			visited[port][pos % m] = pos;
			if(str.charAt(pos % m) == 'L') {
				port = graph[port][0];
			}else {
				port = graph[port][1];
			}
			pos++;
		}
		long total_moves = (long)m * k;
		long start = total_moves - ((total_moves - cyc_start) % cyc_length);
		port = port_start;
		while(start < total_moves) {
			if(str.charAt((int)(start % m)) == 'L') {
				port = graph[port][0];
			}else {
				port = graph[port][1];
			}
			start++;
		}
		out.println(port + 1);
		in.close();
		out.close();
	}
}
