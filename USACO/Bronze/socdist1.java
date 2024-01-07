import java.util.*;
import java.io.*;

public class socdist1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("socdist1.in"));
		PrintWriter out = new PrintWriter("socdist1.out");
		int n = Integer.parseInt(in.readLine());
		String stalls = in.readLine();
		int prev1 = -1;
		ArrayList<Integer> gaps = new ArrayList<Integer>();
		ArrayList<Integer> ones = new ArrayList<Integer>();
		ArrayList<Integer> twos = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			int val = Character.getNumericValue(stalls.charAt(i));
			if(val == 1) {
				if(prev1 != -1) {
					gaps.add(i - prev1);
					ones.add(usualgap(i - prev1, 1));
					twos.add(usualgap(i - prev1, 2));
				}else {
					ones.add(edgegap(i - prev1, 1));
					twos.add(edgegap(i - prev1, 2));
				}
				prev1 = i;
			}
		}
		ones.add(edgegap(n - prev1, 1));
		twos.add(edgegap(n - prev1, 2));
		Collections.sort(gaps);
		Collections.sort(ones);
		Collections.sort(twos);
		if(ones.size() <= 1) {
			out.println(n - 1);
		}else {
			int reduce = Math.max(Math.min(ones.get(ones.size() - 1), ones.get(ones.size() - 2)), twos.get(twos.size() - 1));
			int res = reduce;
			if(gaps.size() > 0) {
				res = Math.min(res, gaps.get(0));
			}
			out.println(res);
		}
		in.close();
		out.close();
	}
	static int edgegap(int gap, int numplace) {
		if(numplace > gap - 1) return 0;
		int ret = (gap - numplace - 1) / numplace;
		return ret + 1;
	}
	static int usualgap(int gap, int numplace) {
		if(numplace > gap - 1) return 0;
		int ret = (gap - 2) / (numplace + 1);
		return ret + 1;
	}
}
