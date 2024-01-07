import java.util.*;
import java.io.*;

public class _1466_E {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int[] arr = new int[] {4, 3, 6, 2, 6, 1, 9, 4};
		for(int x = 0; x <= 5; x++) {
			int sum = 0;
			for(int i = 0; i < arr.length; i++) {
				for(int j = 0; j < arr.length; j++) {
					sum += (arr[i] & arr[x]) * (arr[x] | arr[j]);
				}
			}
			out.println(x + " " + sum);
		}
		
		in.close();
		out.close();
	}
}
