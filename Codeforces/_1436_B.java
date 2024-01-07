import java.util.*;
import java.io.*;

public class _1436_B {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			if(n % 2 == 0) {
				for(int j = 0; j < n; j++) {
					for(int k = 0; k < n; k++) {
						if(k == j || (n - k - 1) == j) {
							out.print(1 + " ");
						}else {
							out.print(0 + " ");
						}
					}
					out.println();
				}
			}else {
				for(int j = 0; j < n; j++) {
					for(int k = 0; k < n; k++) {
						if(j == 0) {
							if(k == 0 || k == n - 1) {
								out.print(1 + " ");
							}else {
								out.print(0 + " ");
							}
						}else {
							if(k == j || k == j - 1) {
								out.print(1 + " ");
							}else {
								out.print(0 + " ");
							}
						}
					}
					out.println();
				}
			}
		}
		in.close();
		out.close();
	}
}
