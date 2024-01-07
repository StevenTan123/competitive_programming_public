import java.util.*;
import java.io.*;

public class circlecross {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
		int n = Integer.parseInt(in.readLine());
		int[] cows = new int[2*n];
		HashMap<Integer, ArrayList<Integer>> indexes = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < 2*n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
			if(indexes.containsKey(cows[i])) {
				indexes.get(cows[i]).add(i);
			}else {
				ArrayList<Integer> curind = new ArrayList<Integer>();
				curind.add(i);
				indexes.put(cows[i], curind);
			}
		}
		in.close();
		
		int[] bit = new int[2*n + 1];
		HashSet<Integer> active = new HashSet<Integer>();
		
		int crossed = 0;
		for(int i = 0; i < 2*n; i++) {
			if(active.contains(cows[i])) {
				int firstind = indexes.get(cows[i]).get(0);
				update(bit, firstind, -1);
				active.remove(cows[i]);
				int curinter = sum(bit, i) - sum(bit, firstind);
				crossed += curinter;
			}else {
				update(bit, i, 1);
				active.add(cows[i]);
			}
		}
		
		PrintWriter out = new PrintWriter("circlecross.out");
		out.println(crossed);
		out.close();
	}
	static void update(int[] bit, int index, int add) {
		index++;
		while(index < bit.length) {
			bit[index] += add;
			index += index & -index;
		}
	}
	static int sum(int[] bit, int index) {
		index++;
		int res = 0;
		while(index > 0) {
			res += bit[index];
			index -= index & -index;
		}
		return res;
	}
}
