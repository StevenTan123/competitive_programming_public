import java.util.*;
import java.io.*;

public class _1770_A {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            line = new StringTokenizer(in.readLine());
            PriorityQueue<Integer> active = new PriorityQueue<Integer>();
            for (int i = 0; i < n; i++) {
                active.add(Integer.parseInt(line.nextToken()));
            }
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                active.poll();
                active.add(Integer.parseInt(line.nextToken()));
            }
            long sum = 0;
            for (int val : active) {
                sum += val;
            }
            out.println(sum);
		}
		in.close();
		out.close();
	}
}
