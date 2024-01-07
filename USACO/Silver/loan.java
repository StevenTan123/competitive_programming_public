import java.util.*;
import java.io.*;

public class loan {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("loan.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		long n = Long.parseLong(line.nextToken());
		long k = Long.parseLong(line.nextToken());
		long m = Long.parseLong(line.nextToken());
		in.close();
		long leftBound = 1;
		long rightBound = k;
		while(leftBound < rightBound - 1) {
			long middle = (leftBound + rightBound) / 2;
			if(xPays(middle, n, k, m)) {
				leftBound = middle;
			}else {
				rightBound = middle - 1;
			}
		}
		long res = leftBound;
		if(xPays(rightBound, n, k, m)) {
			res = rightBound;
		}
		PrintWriter out = new PrintWriter("loan.out");
		out.println(res);
		out.close();
	}
	static boolean xPays(long x, long n, long k, long m) {
		long g = 0;
		while(k > 0) {
			long y = (n - g) / x;
			if(y <= m) {
				y = m;
				g += k * m;
				if(g >= n) {
					return true;
				}else {
					return false;
				}
			}
			// Example: if n = 1000 and x = 300 then 1000 / 300 = 3
			//										997  / 300 = 3
			//										...
			//										901  / 300 = 3
			//										898  / 300 = 2
			//Looping through would've taken like ~30 iterations, but we 
			//can calculate all times when y doesn't change
			//unchanged = 1000 - 300 * 3 = 100 range where y doesn't change
			//daysunchanged = ceil(100 / 3) = 34 payments where y doesn't change
			//we can now directly skip 34 payments as we know they are all the same
			long unchanged = n - g - x * y;
			long daysunchanged = unchanged / y;
			if(unchanged % y != 0) {
				daysunchanged++;
			}
			if(g >= n) {
				return true;
			}
			if(daysunchanged > 0) {
				k -= daysunchanged;
				g += daysunchanged * y;
			}else {
				g += y;
				k--;
			}
		}
		return false;
	}
}
