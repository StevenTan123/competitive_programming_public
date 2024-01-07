/*
ID: StevenTan
PROB: milk
LANG: JAVA
 */	
import java.util.*;
import java.io.*;
public class milk {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("milk.in"));
		int milkWanted = in.nextInt();
		int moneyCount = 0;
		int[][] farmers = new int[in.nextInt()][2];
		int i = 0;
		while(in.hasNext()) {
			farmers[i / 2][i % 2] = in.nextInt();
			i++;
		}
		in.close();
		for(int j = 0; j < farmers.length; j++) {
			int curInd = j;
			while(curInd > 0 && farmers[curInd][0] < farmers[curInd - 1][0]) {
				int[] temp = farmers[curInd];
				farmers[curInd] = farmers[curInd - 1];
				farmers[curInd - 1] = temp;
				curInd--;
			}
		}
		for(int j = 0; j < farmers.length; j++) {
			int cost = farmers[j][0];
			int avail = farmers[j][1];
			if(milkWanted > avail) {
				milkWanted -= avail;
				moneyCount += cost * avail;
			}else {
				moneyCount += milkWanted * cost;
				milkWanted = 0;
				break;
			}
		}
		PrintWriter out = new PrintWriter("milk.out", "UTF-8");
		out.println(moneyCount);
		out.close();
	}

}
