/*
ID: tanstev1
LANG: JAVA
TASK: sort3
 */
import java.io.*;

public class sort3 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
		int n = Integer.parseInt(in.readLine());
		int[] sequence = new int[n];
		for(int i = 0; i < n; i++) {
			sequence[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		int ones = countNum(1, sequence);
		int twos = countNum(2, sequence);
		PrintWriter out = new PrintWriter("sort3.out");
		out.println(swapValsInto(sequence, 1, 0, ones) + swapValsInto(sequence, 2, ones, ones + twos));
		out.close();
	}
	static int swapValsInto(int[] sequence, int num, int start, int end) {
		int swapCount = 0;
		for(int i = start; i < end; i++) {
			if(sequence[i] != num) {
				if(num == 1 && sequence[i] == 3) {
					for(int j = sequence.length - 1; j >= start; j--) {
						if(sequence[j] == num) {
							swapCount++;
							swap(sequence, i, j);
							break;
						}
					}
				}else {
					for(int j = end; j < sequence.length; j++) {
						if(sequence[j] == num) {
							swapCount++;
							swap(sequence, i, j);
							break;
						}
					}
				}
			}
		}
		return swapCount;
	}
	static void swap(int[] sequence, int a, int b) {
		int temp = sequence[a];
		sequence[a] = sequence[b];
		sequence[b] = temp;
	}
	static int countNum(int num, int[] sequence) {
		int res = 0;
		for(int i = 0; i < sequence.length; i++) {
			if(sequence[i] == num) {
				res++;
			}
		}
		return res;
	}
}
