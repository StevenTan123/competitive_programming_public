import java.util.*;
import java.io.*;

public class cbarn {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		int n = Integer.parseInt(in.readLine());
		int[] barn = new int[n];
		for(int i = 0; i < n; i++) {
			barn[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		int best = 0;
		for(int i = 0; i < n; i++) {
			int res = dropCows(i, n, barn);
			best = Math.max(res, best);
		}
		PrintWriter out = new PrintWriter("cbarn.out");
		out.println(best);
		out.close();
	}
	static int dropCows(int begin, int n, int[] barn) {
		//Set allowing duplicates
		TreeSet<Integer> active = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 > o2) {
					return 1;
				}else {
					return -1;
				}
			}	
		});
		int counter = begin;
		int res = 0;
		while(counter < n || counter % n != begin) {
			for(int i = 0; i < barn[counter % n]; i++) {
				active.add(counter);
			}
			if(active.size() == 0) {
				return -1;
			}
			res += (int)Math.pow(counter - active.pollFirst(), 2);
			counter++;
		}
		return res;
	}
}
