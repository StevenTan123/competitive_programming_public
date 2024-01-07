import java.io.*;
import java.util.*;
public class shuffle_bronze {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("shuffle.in"));
		int length = in.nextInt();
		int[] shufOrder = new int[length];
		int[] cows = new int[length];
		for(int i = 0; i < length; i++) {
			shufOrder[i] = in.nextInt();
		}
		for(int i = 0; i < length; i++) {
			cows[i] = in.nextInt();
		}
		in.close();
		for(int i = 0; i < 3; i++) {
			cows = revShuff(shufOrder, cows);
		}
		PrintWriter out = new PrintWriter("shuffle.out", "UTF-8");
		for(int i = 0; i < length; i++) {
			out.println(cows[i]);
		}
		out.close();
	}
	static int[] revShuff(int[] shufOrder, int[] cows) {
		int[] res = new int[cows.length];
		for(int i = 1; i <= cows.length; i++) {
			int index = 0;
			for(int j = 0; j < shufOrder.length; j++) {
				if(shufOrder[j] == i) {
					index = j;
				}
			}
			res[index] = cows[i - 1];
		}
		return res;
	}
}
