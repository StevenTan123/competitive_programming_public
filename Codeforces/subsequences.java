import java.util.*;
import java.io.*;

public class subsequences {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			String str = in.readLine();
			int subsetcount = 1;
			ArrayList<Integer> end0s = new ArrayList<Integer>();
			ArrayList<Integer> end1s = new ArrayList<Integer>();
			int[] res = new int[n];
			for(int j = 0; j < n; j++) {
				int bit = Character.getNumericValue(str.charAt(j));
				if(bit == 0 && end1s.size() > 0) {
					res[j] = end1s.get(end1s.size() - 1);
					end1s.remove(end1s.size() - 1);
					end0s.add(res[j]);
				}else if(bit == 0) {
					res[j] = subsetcount;
					end0s.add(subsetcount);
					subsetcount++;
				}
				if(bit == 1 && end0s.size() > 0) {
					res[j] = end0s.get(end0s.size() - 1);
					end0s.remove(end0s.size() - 1);
					end1s.add(res[j]);
				}else if(bit == 1) {
					res[j] = subsetcount;
					end1s.add(subsetcount);
					subsetcount++;
				}
			}
			out.println(subsetcount - 1);
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < n - 1; j++) {
				sb.append(res[j]);
				sb.append(" ");
			}
			sb.append(res[n - 1]);
			out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
