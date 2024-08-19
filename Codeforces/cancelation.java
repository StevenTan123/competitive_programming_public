import java.util.*;
import java.io.*;

public class cancelation {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			StringTokenizer line = new StringTokenizer(in.readLine());
			int[] arr = new int[n];
			for(int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(line.nextToken());
			}
			int negpointer = 0;
			long cost = 0;
			for(int j = 0; j < n; j++) {
				if(arr[j] <= 0) {
					continue;
				}
				if(j > negpointer) {
					negpointer = j;
				}
				negpointer = nextpointer(arr, negpointer);
				while(negpointer < n && arr[negpointer] * -1 < arr[j]) {
					arr[j] += arr[negpointer];
					arr[negpointer] = 0;
					negpointer = nextpointer(arr, negpointer);
				}
				if(negpointer >= n) {
					cost += arr[j];
				}else {
					arr[negpointer] += arr[j];
					arr[j] = 0;
				}
			}
			out.println(cost);
		}
		in.close();
		out.close();
	}
	static int nextpointer(int[] arr, int pointer) {
		while(pointer < arr.length && arr[pointer] >= 0) {
			pointer++;
		}
		return pointer;
	}
}
