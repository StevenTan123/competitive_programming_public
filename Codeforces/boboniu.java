import java.util.*;
import java.io.*;

public class boboniu {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int[] a = new int[n];
		int[] b = new int[m];
		String[] as = new String[n];
		String[] bs = new String[m];
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
			as[i] = Integer.toBinaryString(a[i]);
			int length = as[i].length();
			for(int j = 0; j < 9 - length; j++) {
				as[i] = "0" + as[i];
			}
		}
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < m; i++) {
			b[i] = Integer.parseInt(line.nextToken());
			bs[i] = Integer.toBinaryString(b[i]);
			int length = bs[i].length();
			for(int j = 0; j < 9 - length; j++) {
				bs[i] = "0" + bs[i];
			}
		}
		HashSet[] restrictions = new HashSet[n];
		HashSet<Integer> brestrict = new HashSet<Integer>();
		for(int i = 0; i < m; i++) brestrict.add(i);
		for(int i = 0; i < n; i++) restrictions[i] = new HashSet<Integer>(brestrict);
		int res = 0;
		for(int i = 0; i < 9; i++) {
			HashSet[] newres = new HashSet[n];
			boolean ccan0 = true;
			for(int j = 0; j < n; j++) {
				newres[j] = new HashSet<Integer>();
				boolean can0 = false;
				for(Object be : restrictions[j]) {
					String belement = bs[(int) be];
					if(!(as[j].charAt(i) == '1' && belement.charAt(i) == '1')) {
						can0 = true;
						newres[j].add((int) be);
					}
				}
				if(!can0) {
					ccan0 = false;
					break;
				}
			}
			if(ccan0) {
				restrictions = newres;
			}else {
				res += Math.pow(2, 8 - i);
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
}