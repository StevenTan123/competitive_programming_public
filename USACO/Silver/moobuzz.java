import java.io.*;
public class moobuzz {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("moobuzz.in"));
		int n = Integer.parseInt(in.readLine());
		in.close();
		long leftBound = 1;
		long rightBound = Integer.MAX_VALUE;
		long res = -1;
		while(res < 0 || leftBound > rightBound) {
			long average = (leftBound + rightBound) / 2;
			long mooValue = minusMoos(average);
			if(mooValue > n) {
				rightBound = average - 1;
			}else if(mooValue < n) {
				leftBound = average + 1;
			}else {
				res = average;
			}
		}
		PrintWriter out = new PrintWriter("moobuzz.out");
		while(multFiveThree(res)) {
			res--;
		}
		out.println(res);
		out.close();
	}
	static long minusMoos(long n) {
		long multThrees = n / 3;
		long multFives = n / 5;
		long multFifteens = n / 15;
		long res = n - (multThrees + multFives - multFifteens);
		return res;
	}
	static boolean multFiveThree(long n) {
		return n % 3 == 0 || n % 5 == 0;
	}
}
