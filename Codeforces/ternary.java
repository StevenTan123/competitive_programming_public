import java.util.*;
import java.io.*;

public class ternary {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int[] a = new int[3];
			int[] b = new int[3];
			StringTokenizer line = new StringTokenizer(in.readLine());
			StringTokenizer line2 = new StringTokenizer(in.readLine());
			for(int j = 0; j < 3; j++) {
				a[j] = Integer.parseInt(line.nextToken());
				b[j] = Integer.parseInt(line2.nextToken());
			}
			int res = 0;
			int bcounter = 2;
			while(a[0] >= b[bcounter]) {
				a[0] -= b[bcounter];
				b[bcounter] = 0;
				if(bcounter == 2) {
					bcounter = 0;
				}else if(bcounter == 0){
					bcounter = 1;
				}else {
					break;
				}
			}
			b[bcounter] -= a[0];
			a[0] = 0;
			bcounter = 0;
			while(bcounter < 3 && a[1] >= b[bcounter]) {
				if(1 < bcounter) {
					res -= bcounter * b[bcounter];
				}
				a[1] -= b[bcounter];
				b[bcounter] = 0;
				bcounter++;
			}
			if(bcounter < 3) {
				if(1 < bcounter) {
					res -= bcounter * a[1];
				}
				b[bcounter] -= a[1];
				a[1] = 0;
			}
			res += 2 * Math.min(a[2], b[1]);
			out.println(res);
		}
		in.close();
		out.close();
	}
}
