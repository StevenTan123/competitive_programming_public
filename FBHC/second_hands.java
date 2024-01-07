import java.util.*;
import java.io.*;

public class second_hands {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("second_hands_input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		int tt = Integer.parseInt(in.readLine());
		for(int t = 1; t <= tt; t++) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] s = new int[n];
            int[] counts = new int[105];
            boolean possible = true;
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(line.nextToken());
                counts[s[i]]++;
                if(counts[s[i]] > 2) {
                    possible = false;
                }
            }
            String res = "Case #" + t + ": ";
            if(k * 2 >= n && possible) {
                out.println(res + "YES");
            }else {
                out.println(res + "NO");
            }
		}
		in.close();
		out.close();
	}
}
