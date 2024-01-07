import java.util.*;
import java.io.*;

public class _1490_F {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            HashMap<Integer, Integer> freqs = new HashMap<Integer, Integer>();
            for(int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(line.nextToken());
                Integer freq = freqs.get(a[j]);
                if(freq == null) freq = 0;
                freqs.put(a[j], freq + 1);
            }
            //the frequency of each frequency, the key being the frequency, the value being the frequency of the frequency
            TreeMap<Integer, Integer> ffreqs = new TreeMap<Integer, Integer>();
            for(int key : freqs.keySet()) {
                Integer freq = ffreqs.get(freqs.get(key));
                if(freq == null) freq = 0;
                ffreqs.put(freqs.get(key), freq + 1);
            }
            pair[] pairs = new pair[ffreqs.size()];
            int counter = 0;
            for(int key : ffreqs.keySet()) {
                pairs[counter] = new pair(key, ffreqs.get(key));
                counter++;
            }
            int remove = n;
            int freqsum = 0;
            for(int j = 0; j < pairs.length; j++) {
                freqsum += pairs[j].freq2;
            }
            int res = Integer.MAX_VALUE;
            for(int j = 0; j < pairs.length; j++) {
                pair cur = pairs[j];
                int p1 = n - remove;
                remove -= cur.freq1 * cur.freq2;
                freqsum -= cur.freq2;
                res = Math.min(res, p1 + remove - (freqsum * cur.freq1));
            }
            out.println(res);
		}
		in.close();
		out.close();
	}
    static class pair {
        int freq1, freq2;
        pair(int f1, int f2) {
            freq1 = f1;
            freq2 = f2;
        }
    }
}
