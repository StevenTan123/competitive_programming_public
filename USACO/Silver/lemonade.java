import java.util.*;
import java.io.*;
public class lemonade {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("lemonade.in"));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer line = new StringTokenizer(in.readLine());
		PriorityQueue<Integer> cows = new PriorityQueue<>(10, Collections.reverseOrder());
		for(int i = 0; i < n; i++) {
			cows.add(Integer.parseInt(line.nextToken()));
		}
		in.close();
		int count = 0;
		while(cows.size() > 0 && cows.poll() >= count) {
			count++;
		}
		PrintWriter out = new PrintWriter("lemonade.out");
		out.println(count);
		out.close();
	}
}
