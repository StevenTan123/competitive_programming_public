import java.util.*;
import java.io.*;

public class chocolate {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int lpointer = 1;
		int rpointer = n;
		int[] res = new int[n];
		HashSet<Integer> used = new HashSet<Integer>();
		while(lpointer < rpointer) {
			System.out.println("? " + lpointer + " " + rpointer);
			int k1 = Integer.parseInt(in.readLine());
			System.out.println("? " + rpointer + " " + lpointer);
			int k2 = Integer.parseInt(in.readLine());
			if(k2 > k1) {
				res[rpointer - 1] = k2;
				used.add(k2);
				rpointer--;
			}else {
				res[lpointer - 1] = k1;
				used.add(k1);
				lpointer++;
			}
		}
		for(int i = 1; i <= n; i++) {
			if(!used.contains(i)) {
				res[lpointer - 1] = i;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("! ");
		for(int i = 0; i < n - 1; i++) {
			sb.append(res[i]);
			sb.append(" ");
		}
		sb.append(res[n - 1]);
		System.out.println(sb.toString());
	}
}
