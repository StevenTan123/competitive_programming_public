import java.util.*;
import java.io.*;

public class maxcross {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		HashSet<Integer> broken = new HashSet<Integer>();
		for(int i = 0; i < b; i++) {
			broken.add(Integer.parseInt(in.readLine()));
		}
		in.close();
		int min = -1;
		int[] stored = new int[n - k + 1];
		for(int i = 1; i + k - 1 <= n; i++) {
			if(i == 1) {
				int numbroke = 0;
				for(int j = i; j < i + k; j++) {
					if(broken.contains(j)) {
						numbroke++;
					}
				}
				stored[i - 1] = numbroke;
				min = i - 1;
			}else {
				int numbroke = stored[i - 2];
				if(broken.contains(i - 1)) {
					numbroke -= 1;
				}
				if(broken.contains(i + k - 1)) {
					numbroke += 1;
				}
				stored[i - 1] = numbroke;
				if(numbroke < stored[min]) {
					min = i - 1;
				}
			}
		}
		PrintWriter out = new PrintWriter("maxcross.out");
		out.println(stored[min]);
		out.close();
	}
}
