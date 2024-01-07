import java.util.*;
import java.io.*;

public class _1451_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int d = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			int coord = 0;
			while(square_dist(coord, coord) <= d * (long)d) {
				coord += k;
			}
			coord -= k;
			if(square_dist(coord, coord + k) <= d * (long)d) {
				out.println("Ashish");
			}else {
				out.println("Utkarsh");
			}
		}
		in.close();
		out.close();
	}
	static long square_dist(int x, int y) {
		return (x * (long)x) + (y * (long)y);
	}
}
