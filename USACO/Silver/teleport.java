import java.io.*;
import java.util.*;

public class teleport {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("teleport.in"));
		int n = Integer.parseInt(in.readLine());
		TreeSet<slpchg> slopes = new TreeSet<slpchg>();
		long curcost = 0;
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());	
			long a = Integer.parseInt(line.nextToken());
			long b = Integer.parseInt(line.nextToken());
			long costsave = Math.abs(a - b) - Math.abs(a);
			curcost += Math.abs(a - b);
			if(costsave > 0) {
				slopes.add(new slpchg(-1, b - costsave));
				slopes.add(new slpchg(2, b));
				slopes.add(new slpchg(-1, b + costsave));
			}
		}
		in.close();
		long res = Long.MAX_VALUE;
		slpchg prev = null;
		long curslope = 0;
		for(slpchg c : slopes) {
			if(prev != null) {
				curcost += (c.start - prev.start) * curslope;
				res = Math.min(res, curcost);
			}
			curslope += c.change;
			prev = c;
		}
		PrintWriter out = new PrintWriter("teleport.out");
		out.println(res);
		out.close();
	}
	static class slpchg implements Comparable<slpchg>{
		long start;
		int change;
		slpchg(int change, long start){
			this.change = change;
			this.start = start;
		}
		@Override
		public int compareTo(slpchg o) {
			if(start > o.start) {
				return 1;
			}else {
				return -1;
			}
		}
		
	}
}
