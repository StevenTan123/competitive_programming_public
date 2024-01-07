import java.io.*;
import java.util.*;
public class shuffle {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		int n = Integer.parseInt(in.readLine());
		int[] count = new int[n];
		int[] shuffle = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			shuffle[i] = Integer.parseInt(line.nextToken());
			count[shuffle[i] - 1]++;
		}
		in.close();
		int result = n;
		Deque<Integer> empty = new ArrayDeque<Integer>();
		for(int i = 0; i < n; i++) {
			if(count[i] == 0) {
				result--;
				empty.push(i + 1);
			}
		}
		while(!empty.isEmpty()) {
			int cur = empty.pop();
			if(--count[shuffle[cur - 1] - 1] <= 0) {
				result--;
				empty.push(shuffle[cur - 1]);
			}
		}
		PrintWriter out = new PrintWriter("shuffle.out");
		out.println(result);
		out.close();
	}
}
