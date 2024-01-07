import java.util.*;
import java.io.*;
public class paintbarn {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("paintbarn.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[][][] wall = new int[1001][1001][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(line.nextToken());
			int y1 = Integer.parseInt(line.nextToken());
			int x2 = Integer.parseInt(line.nextToken());
			int y2 = Integer.parseInt(line.nextToken());
			for(int row = y1; row < y2; row++) {
				wall[row][x1][0] += 1;
				wall[row][x2][1] += 1;
			}
		}
		in.close();
		int res = 0;
		for(int i = 0; i < wall.length; i++) {
			int curlayers = 0;
			for(int j = 0; j < wall[i].length; j++) {
				curlayers += wall[i][j][0];
				curlayers -= wall[i][j][1];
				if(curlayers == k) {
					res++;
				}
			}
		}
		PrintWriter out = new PrintWriter("paintbarn.out");
		System.out.println(res);
		out.close();
	}
}
