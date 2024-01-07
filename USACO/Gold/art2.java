import java.util.*;
import java.io.*;

public class art2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("art2.in"));
		int n = Integer.parseInt(in.readLine());
		int[] painting = new int[n];
		for(int i = 0; i < n; i++) {
			painting[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		HashMap<Integer, ArrayList<Integer>> occurences = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < n; i++) {
			if(painting[i] == 0) {
				continue;
			}
			if(occurences.containsKey(painting[i])) {
				occurences.get(painting[i]).set(1, i);
			}else {
				ArrayList<Integer> val = new ArrayList<Integer>();
				val.add(i);
				val.add(i);
				occurences.put(painting[i], val);
			}
 		}
		int max = 0;
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		for(int i = 0; i < n; i++) {
			ArrayList<Integer> val = occurences.get(painting[i]);
			if(val != null && i == val.get(0)) {
				stack.push(painting[i]);
				max = Math.max(max, stack.size());
			}
			if(stack.size() > 0 && stack.peek() != painting[i]) {
				max = -1;
				break;
			}
			if(val != null && i == val.get(1)) {
				stack.pop();
			}
		}
		PrintWriter out = new PrintWriter("art2.out");
		out.println(max);
		out.close();
	}
}
