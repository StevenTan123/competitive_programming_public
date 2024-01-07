import java.util.*;
import java.io.*;

public class cowjog {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowjog.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int t = Integer.parseInt(line.nextToken());
		int[][] cows = new int[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = Integer.parseInt(line.nextToken());
		}
		in.close();
		int grouplead = n - 1;
		int groupcount = 1;
		for(int i = n - 2; i >= 0; i--) {
			double tmeet = (cows[grouplead][0] - cows[i][0]) / (double)(cows[i][1] - cows[grouplead][1]);
			if(tmeet < 0 || tmeet > t) {
				groupcount++;
				grouplead = i;
			}
		}
		PrintWriter out = new PrintWriter("cowjog.out");
		out.println(groupcount);
		out.close();
	}
}

