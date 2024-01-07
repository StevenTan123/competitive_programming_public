import java.util.*;
import java.io.*;

public class _1451_C {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(line.nextToken());
			int k = Integer.parseInt(line.nextToken());
			String a = in.readLine();
			String b = in.readLine();
			int[] aarr = new int[n];
			int[] barr = new int[n];
			for(int j = 0; j < n; j++) {
				aarr[j] = (int) a.charAt(j);
				barr[j] = (int) b.charAt(j);
			}
			HashMap<Integer, Integer> freqsa = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> freqsb = new HashMap<Integer, Integer>();
			for(int j = 0; j < n; j++) {
				Integer val = freqsa.get(aarr[j]);
				if(val == null) val = 0;
				freqsa.put(aarr[j], val + 1);
				
				Integer val2 = freqsb.get(barr[j]);
				if(val2 == null) val2 = 0;
				freqsb.put(barr[j], val2 + 1);
			}
			boolean possible = true;
			for(int akey : freqsa.keySet()) {
				if(freqsa.get(akey) % k == 0) {
					continue;
				}
				if(freqsb.containsKey(akey)) {
					int max = Math.max(freqsa.get(akey), freqsb.get(akey));
					int min = Math.min(freqsa.get(akey), freqsb.get(akey));
					if((max - min) % k != 0) {
						possible = false;
						break;
					}
				}else {
					possible = false;
					break;
				}
			}
			for(int bkey : freqsb.keySet()) {
				if(freqsb.get(bkey) % k == 0) {
					continue;
				}
				if(freqsa.containsKey(bkey)) {
					int max = Math.max(freqsb.get(bkey), freqsa.get(bkey));
					int min = Math.min(freqsb.get(bkey), freqsa.get(bkey));
					if((max - min) % k != 0) {
						possible = false;
						break;
					}
				}else {
					possible = false;
					break;
				}
			}
			if(possible) {
				Arrays.sort(aarr);
				Arrays.sort(barr);
				for(int j = 0; j < n; j++) {
					if(aarr[j] > barr[j]) {
						possible = false;
						break;
					}
				}
				out.println(possible ? "Yes" : "No");
			}else {
				out.println("No");
			}
		}
		in.close();
		out.close();
	}
}
