import java.util.*;
import java.io.*;

public class _1430_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			String s = in.readLine();
			int[] arr = new int[n];
			for(int j = 0; j < n; j++) {
				arr[j] = Character.getNumericValue(s.charAt(j));
			}
			ArrayList<Integer> stretches = new ArrayList<Integer>();
			int[] groups = new int[n];
			for(int j = 0; j < n; j++) {
				if(j == 0) {
					stretches.add(1);
				    groups[j] = 0;
				}else {
					if(arr[j] == arr[j - 1]) {
						int end = stretches.size() - 1;
						stretches.set(end, stretches.get(end) + 1);
						groups[j] = groups[j - 1];
					}else {
						stretches.add(1);
						groups[j] = groups[j - 1] + 1;
					}
				}
			}
			int[] arrs = new int[stretches.size()];
			for(int j = 0; j < stretches.size(); j++) {
			    arrs[j] = stretches.get(j);
			}
			int pointer = 0;
			int count = 0;
			int group = 0;
			for(int j = 0; j < n; j++) {
				while(pointer < arrs.length && arrs[pointer] == 1) {
					pointer++;
				}
				if(pointer >= arrs.length) {
					break;
				}
				arrs[pointer] -= 1;
				if(groups[pointer] <= group) {
				    pointer = group + 1;
				}
				count++;
				group++;
			}
			int total = arrs.length - group;
			int add = total / 2;
			if(total % 2 != 0) {
				add += 1;
			}
			out.println(count + add);
		}
		in.close();
		out.close();
	}
}
