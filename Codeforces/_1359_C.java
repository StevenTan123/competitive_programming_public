import java.util.*;
import java.io.*;
 
public class _1359_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int h = Integer.parseInt(line.nextToken());
			int c = Integer.parseInt(line.nextToken());
			int target = Integer.parseInt(line.nextToken());
			if((h + c) / (double)2 >= target) {
				out.println(2);
				continue;
			}
			long lbound = 0;
			long rbound = (long)Math.pow(10, 18);
			long res = -1;
			long bigger = -1;
			while(lbound < rbound) {
				long avg = (lbound + rbound) / 2;
				double temp = calctemp(avg, h, c);
				if(temp > target) {
					bigger = Math.max(bigger, avg);
					lbound = avg + 1;
				}else if(temp < target) {
					rbound = avg;
				}else {
					res = 2 * avg + 1;
					break;
				}
			}
			if(res > -1) {
				out.println(res);
			}else {
				long num1 = h * (bigger + 1) + c * bigger - target * (2 * bigger + 1);
				long b2 = bigger + 1;
				long num2 = target * (2 * b2 + 1) - h * (b2 + 1) - c * b2;
				long cross1 = num1 * (2 * b2 + 1);
				long cross2 = num2 * (2 * bigger + 1);
				if(cross1 <= cross2) {
					out.println(bigger * 2 + 1);
				}else {
					out.println((bigger + 1) * 2 + 1);
				}
			}
		}
		in.close();
		out.close();
	}
	static double calctemp(long x, int h, int c) {
		return (double)(x + 1) / (2 * x + 1) * h + (double)x / (2 * x + 1) * c;
	}
}