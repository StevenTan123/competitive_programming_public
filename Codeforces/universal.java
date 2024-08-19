import java.util.*;
import java.io.*;

public class universal {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			String s = in.readLine();
			int[] freqs = new int[3];
			for(int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				if(c == 'R') freqs[0]++;
				else if(c == 'P') freqs[1]++;
				else freqs[2]++;
			}
			char c = ' ';
			if(freqs[0] >= freqs[1] && freqs[0] >= freqs[2]) c = 'P';
			else if(freqs[1] >= freqs[0] && freqs[1] >= freqs[2]) c = 'S';
			else c = 'R';
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < s.length(); j++) {
				sb.append(c);
			}
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
