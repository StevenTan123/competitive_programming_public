import java.util.*;
import java.io.*;

public class killjoy {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int x = Integer.parseInt(line.nextToken());
			int[] ratings = new int[n];
			line = new StringTokenizer(in.readLine());
			int infectedstart = 0;
			int sum = 0;
			for(int j = 0; j < n; j++) {
				ratings[j] = Integer.parseInt(line.nextToken());
				if(ratings[j] == x) infectedstart++;
				sum += ratings[j];
			}
			if(infectedstart == n) {
				out.println(0);
				continue;
			} 
			if(infectedstart > 0 || (sum % n == 0 && sum / n == x)) {
				out.println(1);
			}else {
				out.println(2);
			}
		}
		in.close();
		out.close();
	}
}
