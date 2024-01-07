import java.util.*;
import java.io.*;

public class _1466_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[] notes = new int[n];
			TreeMap<Integer, Integer> freqs = new TreeMap<Integer, Integer>();
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				notes[j] = Integer.parseInt(line.nextToken());
				Integer val = freqs.get(notes[j]);
				if(val == null) val = 0;
				freqs.put(notes[j], val + 1);
			}
			int res = 0;
			int prevind = 0;
			for(int key : freqs.keySet()) {
				int curval = freqs.get(key);
				if(curval > 1) {
					if(prevind == key) {
						prevind++;
						res++;
					}else {
						prevind = key + 1;
						res += 2;
					}
				}else {
					if(prevind == key) {
						prevind++;
					}else {
						prevind = key;
					}
					res++;
				}
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
}
