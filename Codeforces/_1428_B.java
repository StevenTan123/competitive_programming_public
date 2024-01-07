import java.util.*;
import java.io.*;

public class _1428_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			String strbelts = in.readLine();
			int[] belts = new int[n];
			for(int j = 0; j < n; j++) {
				char c = strbelts.charAt(j);
				if(c == '>') {
					belts[j] = 1;
				}else if(c == '<') {
					belts[j] = 2;
				}
			}
			boolean trav = false;
			int curnode = 0;
			int count = 0;
			while(count < n) {
				if(belts[curnode] == 0 || belts[curnode] == 1) {
					curnode++;
					if(curnode > n - 1) curnode = curnode - n;
					count++;
				}else {
					break;
				}
			}
			if(count == n && curnode == 0) {
				trav = true;
			}
			curnode = 0;
			count = 0;
			while(count < n) {
				if(belts[curnode] == 0 || belts[curnode] == 2) {
					curnode--;
					if(curnode < 0) curnode = n + curnode;
					count++;
				}else {
					break;
				}
			}
			if(count == n && curnode == 0) {
				trav = true;
			}
			if(trav) {
				out.println(n);
			}else {
				int res = 0;
				for(int j = 0; j < n; j++) {
					int left = j - 1;
					if(left < 0) left = n + left;
					int right = j;
					if(belts[left] == 0 || belts[right] == 0) {
						res++;
					}
				}
				out.println(res);
			}
		}
		in.close();
		out.close();
	}
}
