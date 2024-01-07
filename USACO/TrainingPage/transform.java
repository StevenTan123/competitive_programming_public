/*
ID: StevenTan
LANG: JAVA
PROB: transform
 */
import java.io.*;
import java.util.*;
public class transform {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("transform.in"));
		int arrsize = in.nextInt();
		int[][] before = new int[arrsize][arrsize];
		int[][] after = new int[arrsize][arrsize];
		int counter = 0;
		while(in.hasNext()) {
			String row = in.next();
			if(counter < arrsize) {
				for(int i = 0; i < row.length(); i++) {
					if(row.charAt(i) == '@') {
						before[counter][i] = 1;
					}else {
						before[counter][i] = 0;
					}
				}
			}else {
				for(int i = 0; i < row.length(); i++) {
					if(row.charAt(i) == '@') {
						after[counter - arrsize][i] = 1;
					}else {
						after[counter - arrsize][i] = 0;
					}
				}
			}
			counter++;
		}
		in.close();
		PrintWriter out = new PrintWriter("transform.out", "UTF-8");
		if(equals(rotateninety(before), after)){
			out.print("1");
			out.println();
		}else if(equals(rotateninety(rotateninety(before)), after)) {
			out.print("2");
			out.println();
		}else if(equals(rotateninety(rotateninety(rotateninety(before))), after)) {
			out.print("3");
			out.println();
		}else if(equals(reflectHor(before), after)) {
			out.print("4");
			out.println();
		}else if(equals(rotateninety(reflectHor(before)), after) ||
				equals(rotateninety(rotateninety(reflectHor(before))), after) ||
				equals(rotateninety(rotateninety(rotateninety(reflectHor(before)))), after)) {
			out.print("5");
			out.println();
		}else if(equals(before, after)) {
			out.print("6");
			out.println();
		}else {
			out.print("7");
			out.println();
		}
		out.close();
	}
	public static boolean equals(int[][] one, int[][] two) {
		boolean equal = true;
		for(int i = 0; i < one.length; i++) {
			for(int j = 0; j < one[i].length; j++) {
				if(one[i][j] != two[i][j]) {
					equal = false;
				}
			}
		}
		return equal;
	}
	public static int[][] rotateninety(int[][] array) {
		int[][] rotated = new int[array.length][array[0].length];
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] == 1) {
					int newi = j;
					int newj = i;
					newj = (int)(newj - ((newj - (float) (array.length - 1) / 2) * 2));
					rotated[newi][newj] = 1;
				}
			}
		}
		return rotated;
	}
	public static int[][] reflectHor(int[][] array){
		int[][] rotated = new int[array.length][array[0].length];
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] == 1) {
					int newj = (int)(j - ((j - (float)(array.length - 1) / 2) * 2));
					rotated[i][newj] = 1;
				}
			}
		}
		return rotated;
	}
}
