/*
ID: tanstev1
LANG: JAVA
PROB: combo
 */
import java.util.*;
import java.io.*;

public class combo {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("combo.in"));
		int n = Integer.parseInt(in.readLine());
		int[] fjcombo = new int[3];
		int[] mcombo = new int[3];
		for(int i = 0; i < 2; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			for(int j = 0; j < 3; j++) {
				if(i == 0) {
					fjcombo[j] = Integer.parseInt(line.nextToken());
				}else {
					mcombo[j] = Integer.parseInt(line.nextToken());
				}
			}
		}
		in.close();
		HashSet<Integer> combos = new HashSet<Integer>();
		findCombos(fjcombo, n, combos);
		findCombos(mcombo, n, combos);
		PrintWriter out = new PrintWriter("combo.out");
		out.println(combos.size());
		out.close();
	}
	static void findCombos(int[] comb, int n, HashSet<Integer> combos) {
		for(int i = comb[0] - 2; i <= comb[0] + 2; i++) {
			int adjusti = i;
			while(adjusti <= 0) {
				adjusti += n;
			}
			while(adjusti > n) {
				adjusti -= n;
			}
			for(int j = comb[1] - 2; j <= comb[1] + 2; j++) {
				int adjustj = j;
				while(adjustj <= 0) {
					adjustj += n;
				}
				while(adjustj > n) {
					adjustj -= n;
				}
				for(int k = comb[2] - 2; k <= comb[2] + 2; k++) {
					int adjustk = k;
					while(adjustk <= 0) {
						adjustk += n;
					}
					while(adjustk > n) {
						adjustk -= n;
					}
					int hashcode = adjusti * 101 * 101 + adjustj * 101 + adjustk;
					combos.add(hashcode);
				}
			}
		}
	}
}
