import java.util.*;
import java.io.*;
public class blist {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("blist.in"));
		int[][] cows = new int[in.nextInt()][3];
		for(int i = 0; i < cows.length; i++) {
			for(int j = 0; j < cows[i].length; j++) {
				cows[i][j] = in.nextInt();
			}
		}
		in.close();
		for(int i = 1; i < cows.length; i++) {
			int curind = i;
			while(curind > 0 && cows[curind][0] < cows[curind - 1][0]) {
				int[] temp = cows[curind - 1];
				cows[curind - 1] = cows[curind];
				cows[curind] = temp;
				curind--;
			}
		}
		int tbused = 0;
		int cbused = 0;
		for(int i = 1; i <= 1000; i++) {
			for(int j = 0; j < cows.length; j++) {
				if(i == cows[j][0]) {
					cbused += cows[j][2];
					if(tbused < cbused) {
						tbused = cbused;
					}
				}
				if(i == cows[j][1] + 1) {
					cbused -= cows[j][2];
				}
			}
		}
		PrintWriter out = new PrintWriter("blist.out");
		out.println(tbused);
		out.close();
	}
}
