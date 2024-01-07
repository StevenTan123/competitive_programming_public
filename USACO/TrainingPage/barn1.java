/*
ID: StevenTan
PROB: barn1
LANG: JAVA
 */
import java.io.*;
import java.util.*;
public class barn1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("barn1.in"));
		int boards = in.nextInt();
		int stalls = in.nextInt();
		int ostalls = in.nextInt();
		int[] occstalls = new int[ostalls];
		int i = 0;
		while(in.hasNext()) {
			occstalls[i] = in.nextInt();
			i++;
		}
		int[][] splits;
		if(occstalls.length < boards) {
			splits = new int[occstalls.length][2];
		}else{
			splits = new int[boards][2];
		}
		in.close();
		Arrays.sort(occstalls);
		splits[0] = new int[] {0, occstalls.length - 1};
		for(int j = 0; j < splits.length - 1; j++) {
			int[] max = findMaxDif(occstalls, splits[0][0], splits[0][1]);
			int index = 0;
			for(int k = 1; k < splits.length; k++) {
				if(splits[k] == null) {
					continue;
				}
				int[] gap = findMaxDif(occstalls, splits[k][0], splits[k][1]);
				if(occstalls[gap[1]] - occstalls[gap[0]] > occstalls[max[1]] - occstalls[max[0]]) {
					max = gap;
					index = k;
				}
			}
			int[] left = new int[] {splits[index][0], max[0]};
			int[] right = new int[] {max[1], splits[index][1]};
			splits[index] = left;
			int[] prev = right;
			for(int l = index + 1; l < splits.length; l++) {
				int[] temp = splits[l];
				splits[l] = prev;
				prev = temp;
			}
		}
		int sum = 0;
		for(int j = 0; j < splits.length; j++) {
			sum += (occstalls[splits[j][1]] - occstalls[splits[j][0]]) + 1;
		}
		PrintWriter out = new PrintWriter("barn1.out", "UTF-8");
		out.println(sum);
		out.close();
	}
	public static int[] findMaxDif(int[] occstalls, int start, int end){
		int leftBound = start;
		int rightBound = start;
		for(int j = start + 1; j <= end; j++) {
			int dif = occstalls[j] - occstalls[j - 1];
			if(dif > occstalls[rightBound] - occstalls[leftBound]) {
				leftBound = j - 1;
				rightBound = j;
			}
		}
		return new int[] {leftBound, rightBound};
	}
	public static boolean isFull(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == null) {
				return false;
			}
		}
		return true;
	}
}
