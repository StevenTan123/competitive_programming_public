import java.util.*;
import java.io.*;

public class _1294_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int q = Integer.parseInt(line.nextToken());
		int x = Integer.parseInt(line.nextToken());
		TreeSet<freq> freqs = new TreeSet<freq>();
		freq[] afreqs = new freq[x];
		for(int i = 0; i < x; i++) {
			afreqs[i] = new freq(i, 0);
			freqs.add(afreqs[i]);
		}
		for(int i = 0; i < q; i++) {
			int yi = Integer.parseInt(in.readLine()) % x;
			freqs.remove(afreqs[yi]);
			afreqs[yi].freq++;
			freqs.add(afreqs[yi]);
			freq min = freqs.first();
			out.println((long)min.freq * x + min.residue);
		}
		in.close();
		out.close();
	}
	static class freq implements Comparable<freq> {
		int residue, freq;
		freq(int r, int f) {
			residue = r;
			freq = f;
		}
		@Override
		public int compareTo(freq o) {
			if(freq == o.freq) {
				return residue - o.residue;
			}
			return freq - o.freq;
		}
	}
}
