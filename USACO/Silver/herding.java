import java.util.*;
import java.io.*;
public class herding {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("herding.in"));
		int n = Integer.parseInt(in.readLine());
		int[] cows = new int[n];
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		Arrays.sort(cows);
		int min = minimize(cows);
		int max = maximize(cows);
		PrintWriter out = new PrintWriter("herding.out");
		out.println(min);
		out.println(max);
		out.close();
	}
	static int minimize(int[] cows) {
		if(cows[cows.length - 1] - cows[1] + 1 == cows.length - 1 && cows[1] - cows[0] - 1 >= 2) {
			return 2;
		}
		if(cows[cows.length - 2] - cows[0] + 1 == cows.length - 1 && cows[cows.length - 1] - cows[cows.length - 2] - 1 >= 2) {
			return 2;
		}
		int j = 0;
		int optimal = 0;
		for(int i = 0; i < cows.length; i++) {
			while(j < cows.length - 1 && cows[j + 1] - cows[i] + 1 <= cows.length) {
				j++;
			}
			if(j - i + 1 > optimal) {
				optimal = j - i + 1;
			}
		}
		return cows.length - optimal;
	}
	static int maximize(int[] cows) {
		int sum = 0;
		for(int i = 1; i < cows.length; i++) {
			sum += cows[i] - cows[i - 1] - 1;
		}
		int min;
		if(cows[1] - cows[0] - 1 < cows[cows.length - 1] - cows[cows.length - 2] - 1) {
			min = cows[1] - cows[0] - 1;
		}else {
			min = cows[cows.length - 1] - cows[cows.length - 2] - 1;
		}
		return sum - min;
	}
}
